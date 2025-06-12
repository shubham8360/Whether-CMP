package org.example.project.whether.data.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.project.core.domain.DataError
import org.example.project.core.domain.Result
import org.example.project.core.domain.map
import org.example.project.core.domain.onSuccess
import org.example.project.whether.data.database.WhetherDao
import org.example.project.whether.data.database.entities.WhetherEntity
import org.example.project.whether.data.dto.WhetherDto
import org.example.project.whether.data.mapper.toEntity
import org.example.project.whether.data.mapper.toModel
import org.example.project.whether.data.network.RemoteDataSource
import org.example.project.whether.domain.WhetherRepository
import org.example.project.whether.domain.models.Whether

class DefaultRepository(
    private val remoteBookDataSource: RemoteDataSource,
    private val whetherDao: WhetherDao
) : WhetherRepository {
    override suspend fun fetchWhetherUpdates(map: Map<String, Any>): Result<Whether, DataError.Remote> {
        return remoteBookDataSource.fetchWhetherUpdates(map).map(WhetherDto::toEntity).onSuccess {
            whetherDao.insertWhetherData(it)
        }.map { it.toModel() }
    }

    override fun fetchCachedWhetherUpdates(): Flow<Whether?> {
        return whetherDao.getLatestWhetherData().map { it?.toModel() }
    }
}