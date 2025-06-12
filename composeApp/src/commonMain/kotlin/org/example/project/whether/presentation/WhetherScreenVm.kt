package org.example.project.whether.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.core.domain.DataError
import org.example.project.core.domain.onError
import org.example.project.core.domain.onSuccess
import org.example.project.core.presentation.toUiText
import org.example.project.whether.data.location.LocationProvider
import org.example.project.whether.data.location.LocationState
import org.example.project.whether.domain.WhetherRepository
import org.example.project.whether.domain.models.Whether

class WhetherScreenVm(
    private val repo: WhetherRepository,
    private val locationState: LocationProvider,
) : ViewModel() {
    private val _whetherState: MutableStateFlow<WhetherState> =
        MutableStateFlow(WhetherState.Loading)

    val whetherState: StateFlow<WhetherState> = _whetherState.onStart {
        fetchWhetherUpdate(localCoordinates)
        observeCachedWhetherUpdates()
    }.stateIn(viewModelScope, WhileSubscribed(5000), WhetherState.Loading)

    companion object {
        private const val TAG = "WhetherScreenVm"
    }

    val locationUpdates = locationState().onEach { state ->
        when (state) {
            is LocationState.Error -> Unit
            LocationState.Loading -> Unit
            is LocationState.Success -> {
                fetchWhetherUpdate(
                    coordinates = Coordinates(state.latitude, state.longitude)
                )
            }
        }
    }.distinctUntilChanged().stateIn(viewModelScope, WhileSubscribed(5000), LocationState.Loading)


    private var localCoordinates =
        Coordinates(51.4779, -0.0015)   //initially it point s to prime meridian location
    private var job: Job? = null


    fun observeCachedWhetherUpdates() {
        repo.fetchCachedWhetherUpdates().onEach { cachedState ->
            cachedState?.let { state ->
                println("The new cached whether has id : ${state.id}")
                _whetherState.update { WhetherState.Success(state) }
            }
        }.launchIn(viewModelScope)
    }

    fun fetchWhetherUpdate(coordinates: Coordinates) {
        if (coordinates == localCoordinates) {
//            with that lat long request are already in queue
            return
        }
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            localCoordinates = coordinates
            val map = mapOf(
                "latitude" to coordinates.latitude,
                "longitude" to coordinates.longitude
            )
            repo.fetchWhetherUpdates(map).onSuccess { newState ->
                _whetherState.update { WhetherState.Success(newState) }
            }.onError { remoteError: DataError.Remote ->
                _whetherState.update {
                    if (it is WhetherState.Loading){
                        WhetherState.Error(error = remoteError.toUiText())
                    }else{
                        it
                    }
                }
                println("$TAG something went wrong ${remoteError.toUiText()}")
            }
        }
    }
}