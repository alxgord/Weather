package com.example.artto.weather.ui.map

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.artto.weather.ui.base.BaseMvpView

interface MapView : BaseMvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun getMapAsync()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun getFusedLocation()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun requestPermissions(permissions: List<String>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setMarker(latitude: Double, longitude: Double)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showSaveButton(show: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun sendResult(latitude: Double, longitude: Double)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(textResId: Int)
}