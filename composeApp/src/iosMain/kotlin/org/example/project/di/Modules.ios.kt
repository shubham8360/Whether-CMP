package org.example.project.di

import org.example.project.location.IosLocationProviderImpl
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.example.project.whether.data.location.LocationProvider
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() =module {
        single<HttpClientEngine> { Darwin.create() }
        single<LocationProvider> { IosLocationProviderImpl() }
    }