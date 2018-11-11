package com.example.artto.weather.di.map

import com.example.artto.weather.di.scope.ViewScope
import com.example.artto.weather.ui.map.MapPresenter
import dagger.Subcomponent

@Subcomponent(modules = [MapModule::class])
@ViewScope
interface MapComponent {

    fun presenter(): MapPresenter
}