package com.example.artto.weather.ui.map

import android.Manifest
import com.arellomobile.mvp.InjectViewState
import com.example.artto.weather.R
import com.example.artto.weather.ui.base.BaseMvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

@InjectViewState
class MapPresenter(private val interact: MapInteract) : BaseMvpPresenter<MapView>() {

    private var location = Pair(0.0, 0.0)

    override fun onFirstViewAttach() = viewState.getMapAsync()

    fun onMyLocationClicked() = viewState.getFusedLocation()

    fun onMapReady() {
        viewState.getFusedLocation()
        viewState.showToast(R.string.map_long_click_hint)
    }

    fun onSaveClicked() {
        interact.saveLocation(location.first, location.second)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = { viewState.sendResult(location.first, location.second) },
                onError = {}
            )
    }

    fun onLocationReceived(latitude: Double, longitude: Double) {
        location = latitude to longitude
        viewState.setMarker(latitude, longitude)
        viewState.showSaveButton(true)
    }

    fun onPermissionNotGranted() = viewState.requestPermissions(
        listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

}