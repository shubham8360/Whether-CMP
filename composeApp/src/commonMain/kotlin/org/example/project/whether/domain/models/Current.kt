package org.example.project.whether.domain.models


data class Current(
     val interval: Int,
     val isDay: Int=1,
     val pressureMsl: Double,
     val relativeHumidity2m: Int,
     val temperature2m: Double,
     val time: String,
     val visibility: Double,
     val weatherCode: Int,
     val windSpeed10m: Double
)