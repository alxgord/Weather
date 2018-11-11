package com.example.artto.weather.di.map

import com.example.artto.weather.data.database.WeatherCacheRepository
import com.example.artto.weather.data.location.LocationRepository
import com.example.artto.weather.di.scope.ViewScope
import com.example.artto.weather.ui.map.MapInteract
import com.example.artto.weather.ui.map.MapPresenter
import dagger.Module
import dagger.Provides

@Module
class MapModule {

    @Provides
    @ViewScope
    fun interact(locationRepository: LocationRepository, weatherCacheRepository: WeatherCacheRepository) =
        MapInteract(locationRepository, weatherCacheRepository)

    @Provides
    @ViewScope
    fun presenter(interact: MapInteract) = MapPresenter(interact)
}