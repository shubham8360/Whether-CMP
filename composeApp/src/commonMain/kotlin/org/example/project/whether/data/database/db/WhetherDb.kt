package org.example.project.whether.data.database.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.example.project.whether.data.database.WhetherDao
import org.example.project.whether.data.database.converters.DoubleListTypeConverter
import org.example.project.whether.data.database.converters.IntListTypeConverter
import org.example.project.whether.data.database.converters.StringListTypeConverter
import org.example.project.whether.data.database.entities.WhetherEntity

@Database(
    entities = [WhetherEntity::class],
    version = 1
)
@TypeConverters(
    StringListTypeConverter::class,
    DoubleListTypeConverter::class,
    IntListTypeConverter::class
)
@ConstructedBy(WhetherDatabaseConstructor::class)
abstract class WhetherDb : RoomDatabase() {
    abstract val  whetherDao: WhetherDao

    companion object {
        const val DB_NAME = "whether.db"
    }
}