package org.example.project

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import org.example.project.utils.PermissionUtils.locationPermission

class MainActivity : ComponentActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val view= LocalView.current
            val isDark=isSystemInDarkTheme()
            if (!view.isInEditMode) {
                SideEffect {
                    val window = (view.context as Activity).window
                    WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =isDark.not()
                }
            }
            val locationPermissionState =
                rememberMultiplePermissionsState(locationPermission.asList())
            LaunchedEffect(locationPermissionState.allPermissionsGranted) {
                if (!locationPermissionState.allPermissionsGranted && !locationPermissionState.shouldShowRationale) {
                    locationPermissionState.launchMultiplePermissionRequest()
                }else{
                    Log.d(TAG, "onCreate: Location Permission")
                }
            }
            val canSubscribeForLocation by  remember(locationPermissionState.allPermissionsGranted){
                derivedStateOf { locationPermissionState.allPermissionsGranted } }

            App(canSubscribeForLocation)
        }
    }
}
