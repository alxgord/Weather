package com.example.artto.weather.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.artto.weather.ApplicationLoader
import com.example.artto.weather.R
import com.example.artto.weather.di.main.MainActivityModule
import com.example.artto.weather.ui.forecast.ForecastFragment
import com.example.artto.weather.ui.forecast.ForecastRouter
import com.example.artto.weather.ui.map.MapActivity

class MainActivity : MvpAppCompatActivity(), MainView, ForecastRouter {

    companion object {
        const val START_ACTIVITY_REQUEST = 111
    }

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter =
        ApplicationLoader.applicationComponent.main(MainActivityModule()).presenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun navigateToMap() = startActivityForResult(Intent(this, MapActivity::class.java), START_ACTIVITY_REQUEST)

    override fun navigateToForecast(latitude: Double, longitude: Double) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.coordinator_layout_main_container, ForecastFragment.newInstance(latitude, longitude))
            .commit()
    }

    override fun closeForecastView() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        presenter.onForecastViewClosed()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == START_ACTIVITY_REQUEST && resultCode == Activity.RESULT_OK) {
            presenter.onLocationReceived(
                data?.getDoubleExtra(MapActivity.KEY_LATITUDE, 0.0),
                data?.getDoubleExtra(MapActivity.KEY_LONGITUDE, 0.0)
            )
        }
    }

}
