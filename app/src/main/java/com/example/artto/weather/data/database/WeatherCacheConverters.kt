package com.example.artto.weather.data.database

import android.arch.persistence.room.TypeConverter
import com.example.artto.weather.data.network.weather.response.forecast.ForecastWeatherResponse
import com.fasterxml.jackson.databind.ObjectMapper

class WeatherCacheConverters {

    @TypeConverter
    fun toString(weather: ForecastWeatherResponse): String = ObjectMapper().writeValueAsString(weather)

    @TypeConverter
    fun fromString(weather: String): ForecastWeatherResponse =
        ObjectMapper().readValue(weather, ForecastWeatherResponse::class.java)

}