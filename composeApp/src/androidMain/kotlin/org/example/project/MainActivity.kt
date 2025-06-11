package org.example.project

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import org.example.utils.PermissionUtils
import org.example.utils.PermissionUtils.locationPermission

class MainActivity : ComponentActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                val notificationPermissionState = rememberPermissionState(
                    android.Manifest.permission.POST_NOTIFICATIONS
                )
                LaunchedEffect(notificationPermissionState.status) {
                    if (!notificationPermissionState.status.isGranted) {
                        notificationPermissionState.launchPermissionRequest()
                    }
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
            App()
        }
    }
}


@Preview
@Composable
fun AppAndroidPreview() {
    App()
}