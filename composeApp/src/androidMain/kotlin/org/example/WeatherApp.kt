package org.example

import android.app.Application
import org.example.project.di.initKoin
import org.koin.android.ext.koin.androidContext


class WeatherApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@WeatherApp)
        }
    }
}