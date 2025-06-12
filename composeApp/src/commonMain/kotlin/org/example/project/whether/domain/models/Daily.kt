package org.example.project.whether.domain.models

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.toLocalDateTime
import org.example.project.whether.domain.formatter.WhetherFormatter.dayFormatter


data class Daily(
    val temperature2mMax: List<String>,
    val temperature2mMin: List<String>,
    val time: List<Time>,
    val weatherCode: List<Int>,
)

data class Time(
    val time: String,
    val parsedDateTime: LocalDate = safeCall(Clock.System.now().toLocalDateTime(TimeZone.UTC).date) {
        LocalDate.parse(time)
    } ,
    val formattedTime: String = safeCall( time) {
        parsedDateTime.format(dayFormatter)
    }
)