package org.example.project.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.example.project.core.data.HttpClientFactory
import org.example.project.whether.data.database.db.DatabaseFactory
import org.example.project.whether.data.database.db.WhetherDb
import org.example.project.whether.data.network.KtorRemoteDataSource
import org.example.project.whether.data.network.RemoteDataSource
import org.example.project.whether.data.repo.DefaultRepository
import org.example.project.whether.domain.WhetherRepository
import org.example.project.whether.presentation.WhetherScreenVm
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModules = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteDataSource).bind<RemoteDataSource>()
    singleOf(::DefaultRepository).bind<WhetherRepository>()
    viewModelOf(::WhetherScreenVm)
    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<WhetherDb>().whetherDao }

}