package com.example.artto.weather.ui.forecast

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.artto.weather.ui.base.BaseMvpView

interface ForecastView : BaseMvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProgressBar(show: Boolean)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setLocationLabel(location: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun notifyDataSetChanged()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showRetrySnackbar()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun closeForecastView()

}