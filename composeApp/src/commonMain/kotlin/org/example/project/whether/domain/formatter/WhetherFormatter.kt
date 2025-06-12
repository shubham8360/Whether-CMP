package org.example.project.whether.domain.formatter

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char

object WhetherFormatter {

    /**
     * formatter for the date and month eg: Wednesday, June*/
    val dateMonthFormatter = LocalDateTime.Format {
        dayOfWeek(DayOfWeekNames.ENGLISH_FULL)
        char(',')
        char(' ')
        monthName(MonthNames.ENGLISH_FULL)
    }
    /** formatter to formate LocalDateTime in time eg: 10:00 AM, 12:00PM*/

    val timeFormatter = LocalDateTime.Format {
        amPmHour(Padding.ZERO)
        char(':')
        minute(padding = Padding.ZERO)
        char(' ')
        amPmMarker("AM", "PM")
    }

    /** Extracts full name from [LocalDate] in full abbrevation like Monday ,Sunday*/
    @OptIn(FormatStringsInDatetimeFormats::class)
    val dayFormatter = LocalDate.Format {
        dayOfWeek(DayOfWeekNames.ENGLISH_FULL)
    }

}