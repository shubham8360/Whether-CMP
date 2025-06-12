package org.example.project.whether.data.database.entities


data class CurrentUnitsEntity(
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