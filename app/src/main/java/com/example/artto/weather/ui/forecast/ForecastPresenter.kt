package com.example.artto.weather.ui.forecast

import com.arellomobile.mvp.InjectViewState
import com.example.artto.weather.data.network.weather.response.forecast.ListItem
import com.example.artto.weather.ui.base.BaseMvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

@InjectViewState
class ForecastPresenter(private val interact: ForecastInteract) :
    BaseMvpPresenter<ForecastView>(),
    ForecastDayAdapterContract.AdapterPresenter {

    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    private val items: ArrayList<Pair<String, List<ListItem>>> = ArrayList()

    override fun onFirstViewAttach() {
        viewState.showProgressBar(true)
    }

    fun onChangeButtonClicked() = viewState.closeForecastView()

    fun onLocationReceived(latitude: Double?, longitude: Double?) {
        if (latitude == null || longitude == null)
            viewState.closeForecastView()
        else {
            this.latitude = latitude
            this.longitude = longitude
            getWeatherForecast(latitude, longitude)
        }
    }

    private fun getWeatherForecast(latitude: Double, longitude: Double) {
        interact.getWeatherForecast(latitude, longitude)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    if (it.cod != "Error") {
                        viewState.setLocationLabel("${it.city?.name ?: ""} ${it.city?.country ?: ""}")
                        setWeatherData(it.list)
                        viewState.showProgressBar(false)
                    }
                },
                onError = { viewState.showRetrySnackbar() }
            )
            .addTo(disposables)
    }

    private fun setWeatherData(list: List<ListItem>) = with(items) {
        clear()
        addAll(list.groupBy { it.dtTxt?.substringBefore(" ") ?: "" }.toList())
        viewState.notifyDataSetChanged()
    }

    fun onRetryClicked() = getWeatherForecast(latitude, longitude)

    override fun getDayItemCount(): Int = items.size

    override fun onBindDayView(view: ForecastDayAdapterContract.ItemView, position: Int) = view.setData(items[position])

}