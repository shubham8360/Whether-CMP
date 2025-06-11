package org.example.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.util.Log

object PermissionUtils {
    private const val TAG = "PermissionUtils"

    val locationPermission=arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    fun Context.hasPermission(permission: String): Boolean {
        Log.d(TAG, "hasPermission: permission checked $permission")
       return checkSelfPermission(permission)== PackageManager.PERMISSION_GRANTED
    }

    fun Context.hasPermissions(permissions: Array<String>): Boolean {
        for (permission in permissions) {
            if (!hasPermission(permission)) {
                return false
            }
        }
        return true
    }

    fun Context.isGpsEnabled(): Boolean {
        val locationManger = getSystemService(LocationManager::class.java)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            locationManger.isLocationEnabled
        } else locationManger.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }
}