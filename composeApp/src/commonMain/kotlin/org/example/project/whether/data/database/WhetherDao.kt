package org.example.project.whether.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import org.example.project.whether.data.database.entities.WhetherEntity

@Dao
interface WhetherDao {

    @Upsert()
    suspend fun insertWhetherData(whetherEntity: WhetherEntity)

    @Query("SELECT * FROM WhetherEntity ORDER BY id DESC LIMIT 1")
    fun getLatestWhetherData(): Flow<WhetherEntity?>

    @Query("SELECT * FROM WhetherEntity")
    fun getAllWhetherData(): Flow<List<WhetherEntity>>

    @Query("DELETE FROM WhetherEntity")
    suspend fun deleteAllWhetherData()

}