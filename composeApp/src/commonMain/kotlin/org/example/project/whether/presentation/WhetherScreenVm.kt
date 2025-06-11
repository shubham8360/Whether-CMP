package org.example.project.whether.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
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

class WhetherScreenVm(private val repo: WhetherRepository,private val locationState: LocationProvider) : ViewModel() {
    private val _whetherState: MutableStateFlow<WhetherState> = MutableStateFlow(WhetherState.Loading)

    val whetherState: StateFlow<WhetherState> = _whetherState.onStart {
        fetchWhetherUpdate(emptyMap())
    }.stateIn(viewModelScope,WhileSubscribed(5000),WhetherState.Loading)

    companion object{
        private const val TAG = "WhetherScreenVm"
    }

    val locationUpdates=locationState().onEach{state->
        when(state){
            is LocationState.Error -> Unit
            LocationState.Loading -> Unit
            is LocationState.Success -> {
                repo.fetchWhetherUpdates(mapOf("latitude" to state.latitude,"longitude" to state.longitude))

            }
        }
    }.stateIn(viewModelScope,WhileSubscribed(5000),LocationState.Loading)




    fun fetchWhetherUpdate(map:Map<String, Any>){
        viewModelScope.launch (Dispatchers.IO){
            repo.fetchWhetherUpdates(map).onSuccess {newState->
               _whetherState.update { WhetherState.Success(newState) }
            }.onError { remoteError: DataError.Remote ->
                _whetherState.update { WhetherState.Error(error = remoteError.toUiText()) }
                println("$TAG something went wrong ${remoteError.toUiText()}")
            }
        }
    }
}