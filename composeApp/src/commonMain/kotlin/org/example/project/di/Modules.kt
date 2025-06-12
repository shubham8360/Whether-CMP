package org.example.project.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.example.project.core.data.HttpClientFactory
import org.example.project.whether.data.database.db.DatabaseFactory
import org.example.project.whether.data.database.db.WeatherDb
import org.example.project.whether.data.network.KtorRemoteDataSource
import org.example.project.whether.data.network.RemoteDataSource
import org.example.project.whether.data.repo.DefaultRepository
import org.example.project.whether.domain.WeatherRepository
import org.example.project.whether.presentation.WeatherScreenVm
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModules = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteDataSource).bind<RemoteDataSource>()
    singleOf(::DefaultRepository).bind<WeatherRepository>()
    viewModelOf(::WeatherScreenVm)
    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<WeatherDb>().weatherDao }

}