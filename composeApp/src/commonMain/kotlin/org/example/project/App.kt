package org.example.project

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.example.project.whether.presentation.WhetherScreenRoot
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import whethercmp.composeapp.generated.resources.Res
import whethercmp.composeapp.generated.resources.app_name

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App(canSubscribeForLocation: Boolean = false) {
    val snackBarHostState = remember { SnackbarHostState() }
    MaterialTheme {
        Scaffold(modifier = Modifier, snackbarHost = { SnackbarHost(snackBarHostState) }, topBar = {
            WhetherAppBar()
        }) { innerPadding ->
            WhetherScreenRoot(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                snackBarHostState,
                canSubscribeForLocation
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhetherAppBar(modifier: Modifier = Modifier) {
    TopAppBar(modifier = modifier, title = {
        Text(text = stringResource(Res.string.app_name))
    })
}
