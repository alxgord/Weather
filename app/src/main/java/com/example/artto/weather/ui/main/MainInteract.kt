package com.example.artto.weather.ui.main

import com.example.artto.weather.data.location.Location
import com.example.artto.weather.data.location.LocationRepository
import io.reactivex.Single

class MainInteract(private val locationRepository: LocationRepository) {

    fun getLocation(): Single<Location> = locationRepository.locationSingle

}