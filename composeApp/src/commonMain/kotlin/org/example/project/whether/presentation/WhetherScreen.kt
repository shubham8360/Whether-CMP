package org.example.project.whether.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.example.project.whether.presentation.utils.WeatherScreenContent
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun WhetherScreenRoot(modifier: Modifier = Modifier, viewModel: WhetherScreenVm = koinViewModel()) {
    val state = viewModel.whetherState.collectAsState()
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

