package com.example.artto.weather.data.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.artto.weather.data.network.weather.response.forecast.ForecastWeatherResponse

@Entity(tableName = DatabaseConstants.WEATHER_CACHE_TABLE_NAME)
data class WeatherCacheEntity (

    @PrimaryKey
    @ColumnInfo(name = DatabaseConstants.WEATHER_CACHE_ID)
    val id: Long = 0,

    @ColumnInfo(name = DatabaseConstants.WEATHER_CACHE_WEATHER)
    val weather: ForecastWeatherResponse

)