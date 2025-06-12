package org.example.project.whether.data.database.entities


data class DailyEntity(
    val temperature2mMax: List<Double>,
    val temperature2mMin: List<Double>,
    val time: List<String>,
    val weatherCode: List<Int>,
)
