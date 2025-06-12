package org.example.project.whether.data.mapper

import org.example.project.whether.data.database.entities.CurrentEntity
import org.example.project.whether.data.database.entities.CurrentUnitsEntity
import org.example.project.whether.data.database.entities.DailyEntity
import org.example.project.whether.data.database.entities.DailyUnitsEntity
import org.example.project.whether.data.database.entities.WhetherEntity
import org.example.project.whether.data.dto.CurrentDto
import org.example.project.whether.data.dto.CurrentUnitsDto
import org.example.project.whether.data.dto.DailyDto
import org.example.project.whether.data.dto.DailyUnitsDto
import org.example.project.whether.data.dto.WhetherDto


fun WhetherDto.toEntity(): WhetherEntity {
    return WhetherEntity(0,
        current.toEntity(),
        currentUnits.toEntity(),
        daily.toEntity(),
        dailyUnits.toEntity(),
        elevation,
        generationTimeMs,
        latitude,
        longitude,
        timezone,
        timezoneAbbreviation,
        utcOffsetSeconds,
        )
}

fun CurrentUnitsDto.toEntity(): CurrentUnitsEntity {
    return CurrentUnitsEntity(
        interval,
        isDay,
        pressureMsl,
        relativeHumidity2m,
        temperature2m,
        time,
        visibility,
        weatherCode,
        windSpeed10m,
        shortwaveRadiation
    )
}

fun CurrentDto.toEntity(): CurrentEntity {
    return CurrentEntity(
        interval,
        isDay,
        pressureMsl,
        relativeHumidity2m,
        temperature2m,
        time,
        visibility,
        weatherCode,
        windSpeed10m,
        shortwaveRadiation
    )
}

fun DailyUnitsDto.toEntity(): DailyUnitsEntity {
    return DailyUnitsEntity(temperature2mMax, temperature2mMin, time, weatherCode)
}

fun DailyDto.toEntity(): DailyEntity {
    return DailyEntity(temperature2mMax, temperature2mMin, time, weatherCode)
}
