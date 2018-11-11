package com.example.artto.weather.ui.map

import com.example.artto.weather.data.database.WeatherCacheRepository
import com.example.artto.weather.data.location.Location
import com.example.artto.weather.data.location.LocationRepository
import io.reactivex.Completable

class MapInteract(private val locationRepository: LocationRepository,
                  private val weatherCacheRepository: WeatherCacheRepository) {

    fun saveLocation(latitude: Double, longitude: Double): Completable =
        weatherCacheRepository.clear()
            .flatMapCompletable { locationRepository.update(Location(latitude, longitude, true)) }

}