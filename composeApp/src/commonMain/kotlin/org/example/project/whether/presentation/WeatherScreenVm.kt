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
import org.example.project.whether.domain.WeatherRepository

class WeatherScreenVm(
    private val repo: WeatherRepository,
    private val locationState: LocationProvider,
) : ViewModel() {
    private val _whetherState: MutableStateFlow<WeatherState> =
        MutableStateFlow(WeatherState.Loading)

    val whetherState: StateFlow<WeatherState>  get() = _whetherState.onStart {
        fetchWhetherUpdate(localCoordinates)
        observeCachedWhetherUpdates()
    }.stateIn(viewModelScope, WhileSubscribed(5000), WeatherState.Loading)

    private val _remoteWhetherState: MutableStateFlow<WeatherState> =
        MutableStateFlow(WeatherState.Loading)
    val remoteWhetherState: StateFlow<WeatherState>  get()= _remoteWhetherState.stateIn(
        viewModelScope,
        WhileSubscribed(5000),
        WeatherState.Loading
    )

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
                _whetherState.update { WeatherState.Success(state) }
            }
        }.launchIn(viewModelScope)
    }

    private var fetchedForPrimeMeridian = false
    fun fetchWhetherUpdate(coordinates: Coordinates) {
        if (coordinates == localCoordinates && fetchedForPrimeMeridian) {
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
            fetchedForPrimeMeridian=true
            repo.fetchWhetherUpdates(map).onSuccess { newState ->
                _whetherState.update { WeatherState.Success(newState) }
            }.onError { remoteError: DataError.Remote ->
                _whetherState.update {
                    if (it is WeatherState.Loading){
                        WeatherState.Error(error = remoteError.toUiText())
                    }else{
                        it
                    }
                }
                _remoteWhetherState.update { WeatherState.Error(remoteError.toUiText()) }
                println("$TAG something went wrong ${remoteError.toUiText()}")
            }
        }
    }
}