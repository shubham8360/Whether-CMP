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
import org.example.project.whether.domain.formatter.WhetherFormatter.dateMonthFormatter
import org.example.project.whether.domain.formatter.WhetherFormatter.timeFormatter


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
    val parsedDate: LocalDateTime = safeCall(Clock.System.now().toLocalDateTime(
        TimeZone.UTC
    )) {
        LocalDateTime.parse(current.time).toInstant(
            TimeZone.UTC
        ).toLocalDateTime(TimeZone.currentSystemDefault())
    } ,
    val formattedDateTime: String = safeCall("") {
        parsedDate.format(dateMonthFormatter)
    },
    val formatterTime: String = safeCall("") {
        parsedDate.format(timeFormatter)
    }
){
    val latLongString=buildString {
        append("(")
        append(latitude)
        append("/")
        append(longitude)
        append(")")
    }
}


