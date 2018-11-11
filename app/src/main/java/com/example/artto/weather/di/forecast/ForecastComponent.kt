package com.example.artto.weather.di.forecast

import com.example.artto.weather.di.scope.ViewScope
import com.example.artto.weather.ui.forecast.ForecastPresenter
import dagger.Subcomponent

@Subcomponent(modules = [ForecastModule::class])
@ViewScope
interface ForecastComponent {

    fun presenter(): ForecastPresenter
}