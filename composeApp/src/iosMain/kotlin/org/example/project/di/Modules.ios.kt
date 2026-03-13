package org.project.weather.cmp.di

import org.project.weather.cmp.location.IosLocationProviderImpl
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.project.weather.cmp.whether.data.database.db.DatabaseFactory
import org.project.weather.cmp.whether.data.location.LocationProvider
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() =module {
        single<HttpClientEngine> { Darwin.create() }
        single<LocationProvider> { IosLocationProviderImpl() }
        single { DatabaseFactory() }

    }