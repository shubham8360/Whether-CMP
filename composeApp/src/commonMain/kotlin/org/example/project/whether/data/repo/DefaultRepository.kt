package org.example.project.whether.data.repo

import org.example.project.core.domain.DataError
import org.example.project.core.domain.Result
import org.example.project.core.domain.map
import org.example.project.whether.data.dto.WhetherDto
import org.example.project.whether.data.mapper.toModel
import org.example.project.whether.data.network.RemoteDataSource
import org.example.project.whether.domain.WhetherRepository
import org.example.project.whether.domain.models.Whether

class DefaultRepository(
    private val remoteBookDataSource: RemoteDataSource,
) : WhetherRepository {
    override suspend fun fetchWhetherUpdates(map: Map<String, Any>): Result<Whether, DataError.Remote> {
        return remoteBookDataSource.fetchWhetherUpdates(map).map(WhetherDto::toModel)
    }

}