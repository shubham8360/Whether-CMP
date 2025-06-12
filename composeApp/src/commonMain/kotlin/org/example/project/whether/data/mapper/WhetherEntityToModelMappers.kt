package org.example.project.whether.data.mapper

import org.example.project.whether.data.database.entities.CurrentEntity
import org.example.project.whether.data.database.entities.DailyEntity
import org.example.project.whether.data.database.entities.WhetherEntity
import org.example.project.whether.domain.models.Current
import org.example.project.whether.domain.models.CurrentUnits
import org.example.project.whether.domain.models.Daily
import org.example.project.whether.domain.models.DailyUnits
import org.example.project.whether.domain.models.Time
import org.example.project.whether.domain.models.Whether


fun WhetherEntity.toModel(): Whether {
    return Whether(
        id,
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

fun org.example.project.whether.data.database.entities.CurrentUnitsEntity.toModel(): CurrentUnits {
    return CurrentUnits(
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

fun CurrentEntity.toModel(): Current {
    return Current(
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

fun org.example.project.whether.data.database.entities.DailyUnitsEntity.toModel(): DailyUnits {
    return DailyUnits(temperature2mMax, temperature2mMin, time, weatherCode)
}

fun DailyEntity.toModel(): Daily {
    return Daily(temperature2mMax, temperature2mMin, time.map { Time(it) }, weatherCode)
}
