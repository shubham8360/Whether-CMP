package org.example.project.whether.domain.models

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime


@OptIn(FormatStringsInDatetimeFormats::class)
val dayFormatter = LocalDate.Format {
    dayOfWeek(DayOfWeekNames.ENGLISH_FULL)
}

data class Daily(
    val temperature2mMax: List<Double>,
    val temperature2mMin: List<Double>,
    val time: List<Time>,
    val weatherCode: List<Int>,
)

data class Time(
    val time: String,
    val parsedDateTime: LocalDate = runCatching {
        LocalDate.parse(time)
    }.getOrNull() ?: Clock.System.now().toLocalDateTime(TimeZone.UTC).date,
    val formattedTime: String = runCatching {
        parsedDateTime.format(dayFormatter)
    }.getOrNull() ?: time
)