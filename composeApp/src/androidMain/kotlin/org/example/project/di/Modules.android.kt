package org.example.project.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.example.FusedLocationProvider
import org.example.project.whether.data.database.db.DatabaseFactory
import org.example.project.whether.data.location.LocationProvider
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single<HttpClientEngine> { OkHttp.create() }
        single<LocationProvider> { FusedLocationProvider(androidApplication()) }
        single { DatabaseFactory(androidApplication()) }

    }