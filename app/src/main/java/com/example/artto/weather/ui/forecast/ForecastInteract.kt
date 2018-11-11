package com.example.artto.weather.ui.forecast

import com.example.artto.weather.data.database.WeatherCacheEntity
import com.example.artto.weather.data.database.WeatherCacheRepository
import com.example.artto.weather.data.network.weather.WeatherRepository
import com.example.artto.weather.data.network.weather.response.forecast.City
import com.example.artto.weather.data.network.weather.response.forecast.ForecastWeatherResponse
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException

class ForecastInteract(
    private val weatherRepository: WeatherRepository,
    private val weatherCacheRepository: WeatherCacheRepository
) {

    fun getWeatherForecast(latitude: Double, longitude: Double): Flowable<ForecastWeatherResponse> =
        Single.merge(
            weatherCacheRepository.select(0)
                .subscribeOn(Schedulers.io())
                .map { it.weather }
                .onErrorReturn { ForecastWeatherResponse(City("", ""), "Error", listOf()) },
            weatherRepository.getForecast(latitude, longitude)
                .subscribeOn(Schedulers.io())
                .flatMap { weather -> weatherCacheRepository.insert(WeatherCacheEntity(0, weather)).map { weather } }
                .onErrorReturn {
                    if (it is UnknownHostException) throw it
                    else ForecastWeatherResponse(City("", ""), "Error", listOf())
                })

}