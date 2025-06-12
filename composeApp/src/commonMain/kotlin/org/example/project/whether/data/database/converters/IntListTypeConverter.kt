package org.example.project.whether.data.database.converters

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

object IntListTypeConverter {

    @TypeConverter
    fun fromString(value: String): List<Int> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun toString(list: List<Int>): String {
        return Json.encodeToString(list)
    }

}