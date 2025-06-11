package org.example.project.whether.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyUnitsDto(
    @SerialName("temperature_2m_max") val temperature2mMax: String,
    @SerialName("temperature_2m_min") val temperature2mMin: String,
    @SerialName("time") val time: String,
    @SerialName("weather_code") val weatherCode: String
)