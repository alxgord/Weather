package com.example.artto.weather.data.network.weather

import com.example.artto.weather.data.network.weather.response.forecast.ForecastWeatherResponse
import io.reactivex.Single

class WeatherRepository(private val apiMethods: WeatherApiMethods) {

    fun getForecast(latitude: Double, longitude: Double): Single<ForecastWeatherResponse> =
        apiMethods.getForecast(latitude, longitude)

}