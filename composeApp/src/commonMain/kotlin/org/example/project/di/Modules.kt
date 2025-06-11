package org.example.project.di

import org.example.project.core.data.HttpClientFactory
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformModule: Module

val sharedModules = module {
    single { HttpClientFactory.create(get()) }
}