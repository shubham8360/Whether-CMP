package org.example.project.location

import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.example.project.whether.data.location.LocationProvider
import org.example.project.whether.data.location.LocationState
import platform.CoreLocation.*
import platform.Foundation.NSError
import platform.darwin.NSObject

/**
 * [IosLocationProviderImpl] is abstraction for [LocationProvider] fetches location from iOS device.
 * docs refer: @link https://dwirandyh.medium.com/deep-dive-into-core-location-in-ios-a-step-by-step-guide-to-requesting-and-utilizing-user-location-fe8325462ea9
 * */
 class IosLocationProviderImpl : LocationProvider {
    @OptIn(ExperimentalForeignApi::class)
    override operator fun invoke(): Flow<LocationState> = callbackFlow {
        val locationManager = CLLocationManager()

        // Create a delegate object. Note the change in the authorization method name.
        val delegate = object : NSObject(), CLLocationManagerDelegateProtocol {
            override fun locationManager(manager: CLLocationManager, didUpdateLocations: List<*>) {
                val location = didUpdateLocations.firstOrNull() as? CLLocation
                location?.coordinate?.useContents {
                    val latitude=this.latitude
                    val longitude=this.longitude
                    trySend(LocationState.Success(latitude, longitude))
                }
            }

            override fun locationManager(manager: CLLocationManager, didFailWithError: NSError) {
                trySend(LocationState.Error(didFailWithError.localizedDescription ?: "Unknown location error"))
            }

            override fun locationManagerDidChangeAuthorization(manager: CLLocationManager) {
                when (manager.authorizationStatus()) {
                    kCLAuthorizationStatusAuthorizedWhenInUse, kCLAuthorizationStatusAuthorizedAlways -> {
                        // Permissions granted, start updates
                        manager.startUpdatingLocation()
                        // You might want to try to get the last known location here if available
                        manager.location?.coordinate?.let { lastLocation ->
                            trySend(resolveLocationState(lastLocation))
                        }
                    }
                    kCLAuthorizationStatusDenied, kCLAuthorizationStatusRestricted -> {
                        trySend(LocationState.Error("Location permissions denied or restricted."))
                    }
                    kCLAuthorizationStatusNotDetermined -> {
                        // Request permissions when not determined
                        manager.requestWhenInUseAuthorization() // Or requestAlwaysAuthorization()
                        trySend(LocationState.Loading) // Indicate waiting for permission
                    }
                    else -> {
                        // Handle other statuses if necessary (e.g., CLAuthorizationStatusEphemeral)
                        trySend(LocationState.Error("Unexpected location authorization status."))
                    }
                }
            }

        }

        locationManager.delegate = delegate
        locationManager.desiredAccuracy = 1000.0
        locationManager.distanceFilter = kCLDistanceFilterNone

        // Initial check for authorization status (this is still important)
        when (locationManager.authorizationStatus()) {
            kCLAuthorizationStatusAuthorizedWhenInUse, kCLAuthorizationStatusAuthorizedAlways -> {
                locationManager.startUpdatingLocation()
                locationManager.location?.let { lastLocation ->
                    trySend(resolveLocationState(lastLocation.coordinate))
                }
            }
            kCLAuthorizationStatusNotDetermined -> {
                trySend(LocationState.Loading)
                locationManager.requestWhenInUseAuthorization()
            }
            kCLAuthorizationStatusDenied, kCLAuthorizationStatusRestricted -> {
                trySend(LocationState.Error("Location permissions denied or restricted."))
            }
            else -> {
                trySend(LocationState.Error("Unknown initial location authorization status."))
            }
        }

        awaitClose {
            locationManager.stopUpdatingLocation()
            locationManager.delegate = null
        }
    }

     @OptIn(ExperimentalForeignApi::class)
     fun resolveLocationState(cValue: CValue<CLLocationCoordinate2D>): LocationState{
         cValue.useContents {
             return LocationState.Success(latitude,longitude)
         }
     }
}