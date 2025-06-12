package org.example.project.whether.data.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.project.core.domain.DataError
import org.example.project.core.domain.Result
import org.example.project.core.domain.map
import org.example.project.core.domain.onSuccess
import org.example.project.whether.data.database.WeatherDao
import org.example.project.whether.data.dto.WhetherDto
import org.example.project.whether.data.mapper.toEntity
import org.example.project.whether.data.mapper.toModel
import org.example.project.whether.data.network.RemoteDataSource
import org.example.project.whether.domain.WeatherRepository
import org.example.project.whether.domain.models.Weather

class DefaultRepository(
    private val remoteBookDataSource: RemoteDataSource,
    private val weatherDao: WeatherDao
) : WeatherRepository {
    override suspend fun fetchWhetherUpdates(map: Map<String, Any>): Result<Weather, DataError.Remote> {
        return remoteBookDataSource.fetchWhetherUpdates(map).map(WhetherDto::toEntity).onSuccess {
            weatherDao.insertWhetherData(it)
        }.map { it.toModel() }
    }

    override fun fetchCachedWhetherUpdates(): Flow<Weather?> {
        return weatherDao.getLatestWhetherData().map { it?.toModel() }
    }
}