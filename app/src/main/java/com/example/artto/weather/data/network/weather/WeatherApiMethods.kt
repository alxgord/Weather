package com.example.artto.weather.data.network.weather

import com.example.artto.weather.data.network.weather.response.forecast.ForecastWeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiMethods {

    @GET(WeatherApiConstants.PATH_FORECAST)
    fun getForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "metric",
        @Query("appid") appId: String = WeatherApiConstants.API_KEY
    ): Single<ForecastWeatherResponse>

}