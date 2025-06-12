package org.example

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.Log
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import org.example.project.R
import org.example.project.whether.data.location.LocationProvider
import org.example.project.whether.data.location.LocationState
import org.example.utils.PermissionUtils
import org.example.utils.PermissionUtils.hasPermissions
import org.example.utils.PermissionUtils.isGpsEnabled
import java.util.concurrent.Executors
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.time.Duration.Companion.seconds

class FusedLocationProvider(private val context: Context):LocationProvider {
    companion object {
        private const val TAG = "FusedLocationManager"
        private val INTERVALS =  30.seconds
    }

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    private val exceptionalHandler = CoroutineExceptionHandler { _, v ->
        Log.d(TAG,"Fused Location Manager throws: ${v.printStackTrace()} with message ${v.message}")
    }
    private val dispatcher = Dispatchers.IO + exceptionalHandler

    private var isCallbackTriggered = false

    private val priority = Priority.PRIORITY_BALANCED_POWER_ACCURACY
    @SuppressLint("MissingPermission")
    override operator fun invoke():Flow<LocationState> = callbackFlow {
        trySend(LocationState.Loading)

        val locationRequest =
            LocationRequest.Builder(
                priority,
                INTERVALS.inWholeMilliseconds
            ).build()

        val callback = LocationListener { location ->
            isCallbackTriggered = true
            trySend(location.toLocalLocationState())
        }
        val gpsState = context.isGpsEnabled()
        val permissionState = context.hasPermissions(PermissionUtils.locationPermission)
        if (gpsState && permissionState) {
            fusedLocationClient.requestLocationUpdates(
                locationRequest, Executors.newSingleThreadExecutor(), callback
            )
            retrieveLatestFusedLocation()?.let { location ->
                if (isCallbackTriggered) return@let
                Log.d(TAG,"One time request fetched for initial purpose $location")
                trySend(location.toLocalLocationState())
            }
        } else {
            Log.d(TAG,"Failed to fetch: Gps state is $gpsState and permission state is $permissionState")
            trySend(LocationState.Error("Permission not granted"))
        }
        awaitClose {
            fusedLocationClient.removeLocationUpdates(callback)
        }
    }.onStart { emit(LocationState.Loading) }.catch {
       Log.d(TAG,"Fused Location Manager throws: ${it.printStackTrace()}")
        emit(LocationState.Error(it.message?:context.getString(R.string.some_thing_bad_happened_with_location)))
    }.flowOn(dispatcher)


    @SuppressLint("MissingPermission")
    private suspend fun retrieveLatestFusedLocation(): Location? {
        return suspendCoroutine { continuation ->
            runCatching {
                val locationRequest = CurrentLocationRequest.Builder().setPriority(priority).build()
                LocationServices.getFusedLocationProviderClient(context)
                    .getCurrentLocation(locationRequest,
                        object : CancellationToken() {
                            override fun onCanceledRequested(p0: OnTokenCanceledListener): CancellationToken {
                                return CancellationTokenSource().token
                            }

                            override fun isCancellationRequested(): Boolean {
                                return false
                            }
                        }).addOnSuccessListener { location ->
                        continuation.resume(location)
                    }.addOnFailureListener {
                        continuation.resume(null)
                        Log.d(TAG,"retrieveCurrentFusedLocation fails: ${it.printStackTrace()}")
                    }
            }.getOrElse {
                continuation.resume(null)
                Log.d(TAG,"retrieveCurrentFusedLocation fails: ${it.printStackTrace()}")
            }

        }

    }

}

fun Location.toLocalLocationState(): LocationState.Success {
    return LocationState.Success(latitude,longitude)
}

