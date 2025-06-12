package org.example.project.whether.data.database.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.example.project.whether.data.database.WeatherDao
import org.example.project.whether.data.database.converters.DoubleListTypeConverter
import org.example.project.whether.data.database.converters.IntListTypeConverter
import org.example.project.whether.data.database.converters.StringListTypeConverter
import org.example.project.whether.data.database.entities.WeatherEntity

@Database(
    entities = [WeatherEntity::class],
    version = 2
)
@TypeConverters(
    StringListTypeConverter::class,
    DoubleListTypeConverter::class,
    IntListTypeConverter::class
)
@ConstructedBy(WeatherDatabaseConstructor::class)
abstract class WeatherDb : RoomDatabase() {
    abstract val  weatherDao: WeatherDao

    companion object {
        const val DB_NAME = "weather.db"
    }
}