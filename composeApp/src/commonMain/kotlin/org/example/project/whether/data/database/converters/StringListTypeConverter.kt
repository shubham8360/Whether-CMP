package org.example.project.whether.data.database.converters

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

object StringListTypeConverter {

    @TypeConverter
    fun fromString(value: String): List<String> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun toString(list: List<String>): String {
        return Json.encodeToString(list)
    }

}