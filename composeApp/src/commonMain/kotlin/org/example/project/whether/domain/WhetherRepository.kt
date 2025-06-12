package org.example.project.whether.domain

import kotlinx.coroutines.flow.Flow
import org.example.project.core.domain.DataError
import org.example.project.core.domain.Result
import org.example.project.whether.data.database.entities.WhetherEntity
import org.example.project.whether.domain.models.Whether

interface WhetherRepository {

     suspend fun fetchWhetherUpdates(map: Map<String, Any>): Result<Whether, DataError.Remote>

     fun fetchCachedWhetherUpdates(): Flow<Whether?>

}