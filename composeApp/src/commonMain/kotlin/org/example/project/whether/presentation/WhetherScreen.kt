package org.example.project.whether.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.project.core.presentation.UiText
import org.example.project.whether.presentation.utils.WeatherScreenContent
import org.jetbrains.compose.resources.getString
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun WhetherScreenRoot(
    modifier: Modifier = Modifier, snackBarHostState: SnackbarHostState,
    canSubscribeForLocation: Boolean,
    viewModel: WhetherScreenVm = koinViewModel()
) {
    if (canSubscribeForLocation) {
        viewModel.locationUpdates.collectAsStateWithLifecycle()
    }
    val state = viewModel.whetherState.collectAsState()


    val remoteState = viewModel.remoteWhetherState.collectAsState()

    LaunchedEffect(remoteState.value) {
        if (remoteState.value is WhetherState.Error) {
            val uiText = (remoteState.value as WhetherState.Error).error
            when(uiText){
                is UiText.DynamicString -> snackBarHostState.showSnackbar(uiText.value)
                is UiText.StringResourceId -> snackBarHostState.showSnackbar(getString(uiText.id))
            }
        }
    }
    WhetherScreen(modifier = modifier, state = state.value)
}


@Composable
private fun WhetherScreen(modifier: Modifier = Modifier, state: WhetherState) {
    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (state) {
            is WhetherState.Error -> {
                Text(state.error.asString())
            }

            WhetherState.Loading -> {
                CircularProgressIndicator()
            }

            is WhetherState.Success -> {
                WeatherScreenContent(state.whether)
            }
        }

    }
}

