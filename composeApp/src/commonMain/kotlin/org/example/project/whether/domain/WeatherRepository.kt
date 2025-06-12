package org.example.project.whether.domain

import kotlinx.coroutines.flow.Flow
import org.example.project.core.domain.DataError
import org.example.project.core.domain.Result
import org.example.project.whether.domain.models.Weather

interface WeatherRepository {

     suspend fun fetchWhetherUpdates(map: Map<String, Any>): Result<Weather, DataError.Remote>

     fun fetchCachedWhetherUpdates(): Flow<Weather?>

}