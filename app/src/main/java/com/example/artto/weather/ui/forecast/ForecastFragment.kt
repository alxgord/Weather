package com.example.artto.weather.ui.forecast

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.artto.weather.ApplicationLoader
import com.example.artto.weather.R
import com.example.artto.weather.di.forecast.ForecastModule
import com.example.artto.weather.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_forecast.*

class ForecastFragment : BaseFragment(), ForecastView {

    companion object {
        private const val KEY_LOCATION_LATITUDE = "latitude"
        private const val KEY_LOCATION_LONGITUDE = "longitude"

        fun newInstance(latitude: Double, longitude: Double) = ForecastFragment().apply {
            arguments = Bundle().apply {
                putDouble(KEY_LOCATION_LATITUDE, latitude)
                putDouble(KEY_LOCATION_LONGITUDE, longitude)
            }
        }
    }

    @InjectPresenter
    lateinit var presenter: ForecastPresenter

    @ProvidePresenter
    fun providePresenter() = ApplicationLoader.applicationComponent.forecast(ForecastModule()).presenter()

    private lateinit var recyclerAdapter: ForecastDayAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_forecast, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.onLocationReceived(
            arguments?.getDouble(KEY_LOCATION_LATITUDE),
            arguments?.getDouble(KEY_LOCATION_LONGITUDE)
        )

        recyclerAdapter = ForecastDayAdapter(presenter)
        with(recycler_view_forecast) {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        button_forecast_change_location.setOnClickListener { presenter.onChangeButtonClicked() }
    }

    override fun showProgressBar(show: Boolean) {
        val visibility = if (show) View.VISIBLE to View.GONE else View.GONE to View.VISIBLE

        circular_progress_bar_forecast.visibility = visibility.first
        recycler_view_forecast.visibility = visibility.second
        text_view_forecast_location.visibility = visibility.second
        button_forecast_change_location.visibility = visibility.second
    }

    override fun setLocationLabel(location: String) {
        text_view_forecast_location.text = getString(R.string.forecast_location_label, location)
    }

    override fun notifyDataSetChanged() = recyclerAdapter.notifyDataSetChanged()

    override fun showRetrySnackbar() =
        Snackbar.make(constraint_layout_forecast_container, getString(R.string.forecast_snackbar_retry_title), Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.forecast_snackbar_retry_action)) { presenter.onRetryClicked() }
            .show()

    override fun closeForecastView() = (router as ForecastRouter).closeForecastView()

}