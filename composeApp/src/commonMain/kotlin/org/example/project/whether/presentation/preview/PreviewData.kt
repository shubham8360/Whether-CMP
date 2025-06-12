package org.example.project.whether.presentation.preview

import org.example.project.whether.domain.models.Current
import org.example.project.whether.domain.models.CurrentUnits
import org.example.project.whether.domain.models.Daily
import org.example.project.whether.domain.models.DailyUnits
import org.example.project.whether.domain.models.Time
import org.example.project.whether.domain.models.Whether


private val daily= Daily(
    temperature2mMax = listOf("32.0 C","35.0 C","54.0 C"),
    temperature2mMin = listOf("32.0 C","35.0 C","54.0 C"),
    time = listOf("2025-06-12","2025-06-13","2025-06-14").map { Time(it) },
    weatherCode = listOf(1,2,3)
)
private val current = Current(
    time = "2025-06-12T06:30",
    interval = 900,
    temperature2m = 11.2,
    relativeHumidity2m = 88,
    weatherCode = 2,
    windSpeed10m = 8.5, visibility = 13500.0,
    pressureMsl = 1014.9,
    isDay = 0,
    shortwaveRadiation = 0.0
)

private val dailyUnits= DailyUnits(
    temperature2mMax = "°C",
    temperature2mMin = "°C",
    time = "iso8601",
    weatherCode = "wmo code"
)
private val curentUnits= CurrentUnits(
    time = "iso8601",
    interval = "minutes",
    temperature2m = "°C",
    relativeHumidity2m = "%",
    weatherCode = "2",
    isDay = "0",
    windSpeed10m = "km/h",
    visibility = "km",
    pressureMsl = "hPa",
    shortwaveRadiation = "W/m²"
)
val previewWhether = Whether(
    id = 3,
    latitude = 37.4162,
    longitude = -122.0803,
    generationTimeMs = 0.2453232,
    utcOffsetSeconds = -28800,
    timezone = "America/Los_Angeles",
    timezoneAbbreviation = "PST",
    elevation = 236.0,
    current = current,
    daily = daily,
    dailyUnits = dailyUnits,
    currentUnits = curentUnits
)


