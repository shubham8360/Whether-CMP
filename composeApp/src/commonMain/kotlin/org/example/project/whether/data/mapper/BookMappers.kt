package org.example.project.whether.data.mapper

import org.example.project.whether.data.dto.CurrentDto
import org.example.project.whether.data.dto.CurrentUnitsDto
import org.example.project.whether.data.dto.DailyDto
import org.example.project.whether.data.dto.DailyUnitsDto
import org.example.project.whether.data.dto.WhetherDto
import org.example.project.whether.domain.models.Current
import org.example.project.whether.domain.models.CurrentUnits
import org.example.project.whether.domain.models.Daily
import org.example.project.whether.domain.models.DailyUnits
import org.example.project.whether.domain.models.Time
import org.example.project.whether.domain.models.Whether


fun WhetherDto.toModel(): Whether {
    return Whether(
        current.toModel(),
        currentUnits.toModel(),
        daily.toModel(),
        dailyUnits.toModel(),
        elevation,
        generationTimeMs,
        latitude,
        longitude,
        timezone,
        timezoneAbbreviation,
        utcOffsetSeconds,

    )
}

fun CurrentUnitsDto.toModel(): CurrentUnits {
    return CurrentUnits(interval, isDay, pressureMsl, relativeHumidity2m, temperature2m, time, visibility, weatherCode, windSpeed10m)
}

fun CurrentDto.toModel(): Current {
    return Current(
        interval,
        isDay,
        pressureMsl,
        relativeHumidity2m,
        temperature2m,
        time,
        visibility,
        weatherCode,
        windSpeed10m
    )
}

fun DailyUnitsDto.toModel(): DailyUnits {
    return DailyUnits(temperature2mMax, temperature2mMin, time, weatherCode)
}
fun DailyDto.toModel(): Daily {
    return Daily(temperature2mMax, temperature2mMin, time.map { Time(it) }, weatherCode)
}
