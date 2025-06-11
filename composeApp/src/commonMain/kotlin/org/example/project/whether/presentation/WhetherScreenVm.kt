package org.example.project.whether.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.core.domain.DataError
import org.example.project.core.domain.onError
import org.example.project.core.domain.onSuccess
import org.example.project.core.presentation.toUiText
import org.example.project.whether.domain.WhetherRepository

class WhetherScreenVm(private val repo: WhetherRepository) : ViewModel() {
    private val _whetherState: MutableStateFlow<WhetherState> = MutableStateFlow(WhetherState.Loading)

    val whetherState: StateFlow<WhetherState> = _whetherState.onStart {
        fetchWhetherUpdate()
    }.stateIn(viewModelScope,WhileSubscribed(5000),WhetherState.Loading)

    companion object{
        private const val TAG = "WhetherScreenVm"
    }


    fun fetchWhetherUpdate(){
        viewModelScope.launch (Dispatchers.IO){
            repo.fetchWhetherUpdates(emptyMap()).onSuccess {newState->
               _whetherState.update { WhetherState.Success(newState) }
            }.onError { remoteError: DataError.Remote ->
                _whetherState.update { WhetherState.Error(error = remoteError.toUiText()) }
                println("$TAG something went wrong ${remoteError.toUiText()}")
            }
        }
    }
}