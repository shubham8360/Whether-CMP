package org.example.project.whether.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentUnitsDto(
    @SerialName("interval") val interval: String,
    @SerialName("is_day") val isDay: String,
    @SerialName("pressure_msl") val pressureMsl: String,
    @SerialName("relative_humidity_2m") val relativeHumidity2m: String,
    @SerialName("temperature_2m") val temperature2m: String,
    @SerialName("time") val time: String,
    @SerialName("visibility") val visibility: String,
    @SerialName("weather_code") val weatherCode: String,
    @SerialName("wind_speed_10m") val windSpeed10m: String
)