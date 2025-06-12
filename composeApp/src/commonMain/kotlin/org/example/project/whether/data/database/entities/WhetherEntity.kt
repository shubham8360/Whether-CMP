package org.example.project.whether.data.database.entities


data class WhetherEntity(
    val current: CurrentEntity,
    val currentUnits: CurrentUnitsEntity,
    val daily: DailyEntity,
    val dailyUnits: DailyUnitsEntity,
    val elevation: Double,
    val generationTimeMs: Double,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezoneAbbreviation: String,
    val utcOffsetSeconds: Int,
)


