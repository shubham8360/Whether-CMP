package org.example.project.whether.domain.models



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
     val utcOffsetSeconds: Int
)