package org.example.project

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
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
            val locationPermissionState =
                rememberMultiplePermissionsState(locationPermission.asList())
            LaunchedEffect(locationPermissionState.allPermissionsGranted) {
                if (!locationPermissionState.allPermissionsGranted && !locationPermissionState.shouldShowRationale) {
                    locationPermissionState.launchMultiplePermissionRequest()
                }else{
                    Log.d(TAG, "onCreate: Location Permission")
                }
            }
            App(locationPermissionState.allPermissionsGranted)
        }
    }
}


@Preview
@Composable
fun AppAndroidPreview() {
    App()
}