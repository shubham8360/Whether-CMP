package org.example.project.whether.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentDto(
    @SerialName("interval") val interval: Int,
    @SerialName("is_day") val isDay: Int=1,
    @SerialName("pressure_msl") val pressureMsl: Double,
    @SerialName("relative_humidity_2m") val relativeHumidity2m: Int,
    @SerialName("temperature_2m") val temperature2m: Double,
    @SerialName("time") val time: String,
    @SerialName("visibility") val visibility: Double,
    @SerialName("weather_code") val weatherCode: Int,
    @SerialName("wind_speed_10m") val windSpeed10m: Double,
    @SerialName("shortwave_radiation") val shortwaveRadiation: Double
)