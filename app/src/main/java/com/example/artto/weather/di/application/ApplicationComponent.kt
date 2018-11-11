package com.example.artto.weather.di.application

import com.example.artto.weather.di.forecast.ForecastComponent
import com.example.artto.weather.di.forecast.ForecastModule
import com.example.artto.weather.di.data.LocationModule
import com.example.artto.weather.di.main.MainActivityComponent
import com.example.artto.weather.di.main.MainActivityModule
import com.example.artto.weather.di.map.MapComponent
import com.example.artto.weather.di.map.MapModule
import com.example.artto.weather.di.data.WeatherApiModule
import com.example.artto.weather.di.data.WeatherCacheModule
import com.example.artto.weather.di.scope.ApplicationScope
import dagger.Component

@Component(
    modules = [
        ApplicationModule::class,
        WeatherApiModule::class,
        WeatherCacheModule::class,
        LocationModule::class
    ]
)
@ApplicationScope
interface ApplicationComponent {

    fun main(module: MainActivityModule): MainActivityComponent

    fun map(module: MapModule): MapComponent

    fun forecast(module: ForecastModule): ForecastComponent

}