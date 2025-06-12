package org.example.project.whether.domain.models

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

/**
 * formatter for the date and month eg: Wednesday, June*/
val dateMonthFormatter = LocalDateTime.Format {
    dayOfWeek(DayOfWeekNames.ENGLISH_FULL)
    char(',')
    char(' ')
    monthName(MonthNames.ENGLISH_FULL)
}

val timeFormatter = LocalDateTime.Format {
    amPmHour(Padding.ZERO)
    char(':')
    minute(padding = Padding.ZERO)
    char(' ')
    amPmMarker("AM", "PM")
}

data class Whether(
    val current: Current,
    val currentUnits: CurrentUnits,
    val daily: Daily,
    val dailyUnits: DailyUnits,
    val elevation: Double,
    val generationTimeMs: Double,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezoneAbbreviation: String,
    val utcOffsetSeconds: Int,
    val parsedDate: LocalDateTime = runCatching {
        LocalDateTime.parse(current.time).toInstant(
            TimeZone.UTC
        ).toLocalDateTime(TimeZone.currentSystemDefault())
    }.getOrNull()
        ?: Clock.System.now().toLocalDateTime(
            TimeZone.UTC
        ),
    val formattedDateTime: String = runCatching {
        parsedDate.format(dateMonthFormatter)
    }.getOrNull() ?: "",
    val formatterTime: String = runCatching {
        parsedDate.format(timeFormatter)
    }.getOrNull() ?: ""
){
    val latLongString=buildString {
        append("(")
        append(latitude)
        append("/")
        append(longitude)
        append(")")
    }
}
