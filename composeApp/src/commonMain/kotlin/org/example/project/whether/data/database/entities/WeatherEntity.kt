package org.example.project.whether.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true) val id: Long=0,
    @Embedded
    val current: CurrentEntity,
    @Embedded(prefix = "current_units_")
    val currentUnits: CurrentUnitsEntity,
    @Embedded(prefix = "daily_")  val daily: DailyEntity,
    @Embedded(prefix = "daily_units")
    val dailyUnits: DailyUnitsEntity,
    val elevation: Double,
    val generationTimeMs: Double,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezoneAbbreviation: String,
    val utcOffsetSeconds: Int,
)


