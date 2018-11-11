package com.example.artto.weather.ui.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.artto.weather.ApplicationLoader
import com.example.artto.weather.R
import com.example.artto.weather.di.map.MapModule
import com.example.artto.weather.ui.base.BaseActivity
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_map.*


class MapActivity : BaseActivity(), MapView {

    companion object {
        const val KEY_LATITUDE = "latitude"
        const val KEY_LONGITUDE = "longitude"

        const val REQUEST_CHECK_SETTINGS = 101
    }

    @InjectPresenter
    lateinit var presenter: MapPresenter

    @ProvidePresenter
    fun providePresenter() = ApplicationLoader.applicationComponent.map(MapModule()).presenter()

    private lateinit var map: GoogleMap
    private lateinit var locationProvider: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        button_map_save.setOnClickListener { presenter.onSaveClicked() }
        locationProvider = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun getMapAsync() = (supportFragmentManager.findFragmentById(R.id.fragment_map) as SupportMapFragment).getMapAsync {
            map = it
            map.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json))
            map.setOnMyLocationButtonClickListener {
                presenter.onMyLocationClicked()
                false
            }
            map.setOnMapLongClickListener { location -> location?.let { presenter.onLocationReceived(location.latitude, location.longitude) } }
            presenter.onMapReady()
        }

    override fun getFusedLocation() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                getLocation()
            else presenter.onPermissionNotGranted()
        else getLocation()

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        map.isMyLocationEnabled = true
        val request = LocationRequest().apply {
            interval = 10000
            fastestInterval = 10000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(request)
            .setAlwaysShow(true)
            .build()

        LocationServices.getSettingsClient(this).checkLocationSettings(builder).addOnCompleteListener { task ->
            try {
                task.getResult(ApiException::class.java)
                locationProvider.requestLocationUpdates(request, object : LocationCallback() {
                    override fun onLocationResult(result: LocationResult?) {
                        result?.lastLocation?.let { presenter.onLocationReceived(it.latitude, it.longitude) }
                    }
                }, null)
            } catch (e: ApiException) {
                try {
                    if (e.statusCode == LocationSettingsStatusCodes.RESOLUTION_REQUIRED)
                        (e as ResolvableApiException).startResolutionForResult(this, REQUEST_CHECK_SETTINGS)
                } catch (e: Exception) {
                }
            }
        }
    }

    override fun setMarker(latitude: Double, longitude: Double) {
        val latlng = LatLng(latitude, longitude)
        with(map) {
            clear()
            animateCamera(CameraUpdateFactory.newLatLng(latlng))
            addMarker(
                MarkerOptions()
                    .position(latlng)
                    .draggable(true)
            )
        }
    }

    override fun showSaveButton(show: Boolean) {
        button_map_save.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun requestPermissions(permissions: List<String>) =
        permissions.filter { ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED }
            .let { if (it.isNotEmpty()) ActivityCompat.requestPermissions(this, it.toTypedArray(), 0) }

    override fun onRequestPermissionsResult(p0: Int, p1: Array<out String>, p2: IntArray) {
        super.onRequestPermissionsResult(p0, p1, p2)
        requestPermissions(p1.toList())
    }

    override fun sendResult(latitude: Double, longitude: Double) {
        setResult(RESULT_OK, Intent().apply {
            putExtra(KEY_LATITUDE, latitude)
            putExtra(KEY_LONGITUDE, longitude)
        })
        finish()
    }

    override fun showToast(textResId: Int) = Toast.makeText(this, getString(textResId), Toast.LENGTH_LONG).show()
}