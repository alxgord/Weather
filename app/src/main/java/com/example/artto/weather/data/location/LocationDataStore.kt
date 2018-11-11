package com.example.artto.weather.data.location

import android.content.SharedPreferences

class LocationDataStore(private val sharedPreferences: SharedPreferences) {

    companion object {
        const val KEY_LOCATION_LATITUDE = "latitude"
        const val KEY_LOCATION_LONGITUDE = "longitude"
        const val KEY_LOCATION_IS_SAVED = "is_location_saved"
    }

    fun load() = with(sharedPreferences) {
        Location(
            Double.fromBits(getLong(KEY_LOCATION_LATITUDE, 0)),
            Double.fromBits(getLong(KEY_LOCATION_LONGITUDE, 0)),
            getBoolean(KEY_LOCATION_IS_SAVED, false)
        )
    }

    fun save(location: Location) = with(location) {
        sharedPreferences.edit()
            .putLong(KEY_LOCATION_LATITUDE, latitude.toBits())
            .putLong(KEY_LOCATION_LONGITUDE, longitude.toBits())
            .putBoolean(KEY_LOCATION_IS_SAVED, isSaved)
            .apply()
    }

}