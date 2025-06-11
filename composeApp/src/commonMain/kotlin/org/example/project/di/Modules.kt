package org.example.project.di

import org.example.project.core.data.HttpClientFactory
import org.example.project.whether.data.network.KtorRemoteDataSource
import org.example.project.whether.data.network.RemoteDataSource
import org.example.project.whether.data.repo.DefaultRepository
import org.example.project.whether.domain.WhetherRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModules = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteDataSource).bind<RemoteDataSource>()
    singleOf(::DefaultRepository).bind<WhetherRepository>()

}