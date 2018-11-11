package com.example.artto.weather.ui.main

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.artto.weather.ui.base.BaseMvpView

interface MainView : BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToMap()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToForecast(latitude: Double, longitude: Double)

}