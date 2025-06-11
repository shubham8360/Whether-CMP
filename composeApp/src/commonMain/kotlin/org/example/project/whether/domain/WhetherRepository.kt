package org.example.project.whether.domain

import org.example.project.core.domain.DataError
import org.example.project.core.domain.Result
import org.example.project.whether.domain.models.Whether

interface WhetherRepository {

     suspend fun fetchWhetherUpdates(map: Map<String, Any>): Result<Whether, DataError.Remote>
}