package org.example.project.whether.data.database

import androidx.room.Dao
import androidx.room.Upsert

@Dao
interface WhetherDao {

    @Upsert
    fun upsertWhether(whether: Whether)

}