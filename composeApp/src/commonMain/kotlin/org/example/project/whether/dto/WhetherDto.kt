package org.example.project.whether.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WhetherDto(
    @SerialName("current") val current: CurrentDto,
    @SerialName("current_units") val currentUnits: CurrentUnitsDto,
    @SerialName("daily") val daily: DailyDto,
    @SerialName("daily_units") val dailyUnits: DailyUnitsDto,
    @SerialName("elevation") val elevation: Double,
    @SerialName("generationtime_ms") val generationTimeMs: Double,
    @SerialName("latitude") val latitude: Double,
    @SerialName("longitude") val longitude: Double,
    @SerialName("timezone") val timezone: String,
    @SerialName("timezone_abbreviation") val timezoneAbbreviation: String,
    @SerialName("utc_offset_seconds") val utcOffsetSeconds: Int
)