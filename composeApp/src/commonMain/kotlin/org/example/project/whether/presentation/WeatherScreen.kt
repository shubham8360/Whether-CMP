package org.example.project.whether.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.project.core.presentation.UiText
import org.example.project.whether.presentation.utils.TestUtils
import org.example.project.whether.presentation.utils.WeatherScreenContent
import org.jetbrains.compose.resources.getString
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun WeatherScreenRoot(
    modifier: Modifier = Modifier, snackBarHostState: SnackbarHostState,
    canSubscribeForLocation: Boolean,
    viewModel: WeatherScreenVm = koinViewModel()
) {
    if (canSubscribeForLocation) {
        viewModel.locationUpdates.collectAsStateWithLifecycle()
    }
    val state = viewModel.whetherState.collectAsState()


    val remoteState = viewModel.remoteWhetherState.collectAsState()

    LaunchedEffect(remoteState.value) {
        if (remoteState.value is WeatherState.Error) {
            val uiText = (remoteState.value as WeatherState.Error).error
            when(uiText){
                is UiText.DynamicString -> {
                    snackBarHostState.currentSnackbarData
                    snackBarHostState.showSnackbar(uiText.value)
                }
                is UiText.StringResourceId -> snackBarHostState.showSnackbar(getString(uiText.id))
            }
        }
    }
    WeatherScreen(modifier = modifier, state = state.value)
}


@Composable
 fun WeatherScreen(modifier: Modifier = Modifier, state: WeatherState) {

    Column(
        modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (state) {
            is WeatherState.Error -> {
                Text(modifier = Modifier.semantics {
                    contentDescription= TestUtils.errorContentDesc
                }, text = state.error.asString(), textAlign = TextAlign.Center)
            }

            WeatherState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.semantics {
                    contentDescription= TestUtils.loadingContentDesc
                    contentDescription= TestUtils.loadingContentDesc
                })
            }

            is WeatherState.Success -> {
                WeatherScreenContent(modifier= Modifier.semantics {
                    contentDescription= TestUtils.successContentDesc
                },state.whether)
            }
        }

    }
}

