package com.example.artto.weather.ui.main

import com.arellomobile.mvp.InjectViewState
import com.example.artto.weather.ui.base.BaseMvpPresenter
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

@InjectViewState
class MainPresenter(private val interact: MainInteract) : BaseMvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        interact.getLocation()
            .subscribeBy(
                onSuccess = {
                    if (it.isSaved)
                        viewState.navigateToForecast(it.latitude, it.longitude)
                    else viewState.navigateToMap()
                },
                onError = {
                    viewState.navigateToMap()
                })
            .addTo(disposables)
    }

    fun onLocationReceived(latitude: Double?, longitude: Double?) {
        if (latitude == null || longitude == null)
            viewState.navigateToMap()
        else
            viewState.navigateToForecast(latitude, longitude)
    }

    fun onForecastViewClosed() = viewState.navigateToMap()

}