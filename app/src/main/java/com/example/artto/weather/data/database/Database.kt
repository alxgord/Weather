package com.example.artto.weather.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.example.artto.weather.data.network.weather.response.forecast.ForecastWeatherResponse

@Database(entities = [WeatherCacheEntity::class], version = 1)
@TypeConverters(WeatherCacheConverters::class)
abstract class Database : RoomDatabase() {

    abstract fun locationCacheDao(): WeatherCacheDao

}