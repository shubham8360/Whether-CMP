@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package org.example.project.whether.data.database.db

import androidx.room.RoomDatabaseConstructor

expect object WeatherDatabaseConstructor: RoomDatabaseConstructor<WeatherDb> {

    override fun initialize(): WeatherDb

}