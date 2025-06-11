package org.example.project.whether.domain.models


data class CurrentUnits(
    val interval: String,
    val isDay: String,
    val pressureMsl: String,
    val relativeHumidity2m: String,
    val temperature2m: String,
    val time: String,
    val visibility: String,
    val weatherCode: String,
    val windSpeed10m: String,
    val shortwaveRadiation: String,
)