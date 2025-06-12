package org.example.project.whether.data.mapper

import org.example.project.whether.data.database.entities.CurrentEntity
import org.example.project.whether.data.database.entities.DailyEntity
import org.example.project.whether.data.database.entities.WeatherEntity
import org.example.project.whether.domain.models.Current
import org.example.project.whether.domain.models.CurrentUnits
import org.example.project.whether.domain.models.Daily
import org.example.project.whether.domain.models.DailyUnits
import org.example.project.whether.domain.models.Time
import org.example.project.whether.domain.models.Weather


fun WeatherEntity.toModel(): Weather {
    return Weather(
        id,
        current.toModel(),
        currentUnits.toModel(),
        daily.toModel(dailyUnits.temperature2mMax, dailyUnits.temperature2mMin),
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

fun DailyEntity.toModel(maxUnit: String, minUnit: String): Daily {
    val tempMax = temperature2mMax.map { value ->
        buildString {
            append(value)
            append(" ")
            append(maxUnit)
        }
    }
    val temp2Min = temperature2mMin.map { value ->
        buildString {
            append(value)
            append(" ")
            append(minUnit)
        }
    }
    return Daily(tempMax, temp2Min, time.map { Time(it) }, weatherCode)
}
