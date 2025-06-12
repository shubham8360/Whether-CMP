package org.example.project.whether.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import org.example.project.whether.data.database.entities.WeatherEntity

@Dao
interface WeatherDao {

    @Upsert()
    suspend fun insertWhetherData(weatherEntity: WeatherEntity)

    @Query("SELECT * FROM WeatherEntity ORDER BY id DESC LIMIT 1")
    fun getLatestWhetherData(): Flow<WeatherEntity?>

    @Query("SELECT * FROM WeatherEntity")
    fun getAllWhetherData(): Flow<List<WeatherEntity>>

    @Query("DELETE FROM WeatherEntity")
    suspend fun deleteAllWhetherData()

}