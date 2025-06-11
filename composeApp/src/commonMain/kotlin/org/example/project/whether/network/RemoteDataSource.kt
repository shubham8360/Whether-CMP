package org.example.project.whether.network

interface RemoteDataSource {

    suspend fun fetchWhetherUpdates

}