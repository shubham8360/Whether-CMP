package org.example.project.whether.data.network

import org.example.project.core.domain.DataError
import org.example.project.core.domain.Result
import org.example.project.whether.data.dto.WhetherDto

interface RemoteDataSource {

    suspend fun fetchWhetherUpdates(map: Map<String, Any>): Result<WhetherDto, DataError.Remote>

}