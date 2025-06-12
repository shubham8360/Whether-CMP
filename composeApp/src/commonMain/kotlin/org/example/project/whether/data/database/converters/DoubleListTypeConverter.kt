package org.example.project.whether.data.database.converters

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

object DoubleListTypeConverter {

    @TypeConverter
    fun fromString(value: String): List<Double> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun toString(list: List<Double>): String {
        return Json.encodeToString(list)
    }

}