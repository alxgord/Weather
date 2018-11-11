package com.example.artto.weather.di.main

import com.example.artto.weather.data.location.LocationRepository
import com.example.artto.weather.di.scope.ViewScope
import com.example.artto.weather.ui.main.MainInteract
import com.example.artto.weather.ui.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    @ViewScope
    fun interact(locationRepository: LocationRepository) = MainInteract(locationRepository)

    @Provides
    @ViewScope
    fun presenter(interact: MainInteract) = MainPresenter(interact)
}