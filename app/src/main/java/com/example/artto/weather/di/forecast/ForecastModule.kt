package com.example.artto.weather.di.forecast

import com.example.artto.weather.data.database.WeatherCacheRepository
import com.example.artto.weather.data.network.weather.WeatherRepository
import com.example.artto.weather.di.scope.ViewScope
import com.example.artto.weather.ui.forecast.ForecastInteract
import com.example.artto.weather.ui.forecast.ForecastPresenter
import dagger.Module
import dagger.Provides

@Module
class ForecastModule {

    @Provides
    @ViewScope
    fun interact(weatherRepository: WeatherRepository, weatherCacheRepository: WeatherCacheRepository) =
        ForecastInteract(weatherRepository, weatherCacheRepository)

    @Provides
    @ViewScope
    fun presenter(interact: ForecastInteract) = ForecastPresenter(interact)
}