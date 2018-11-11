package com.example.artto.weather.di.data

import android.content.Context
import android.content.SharedPreferences
import com.example.artto.weather.data.location.LocationDataStore
import com.example.artto.weather.data.location.LocationRepository
import com.example.artto.weather.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class LocationModule {

    @Provides
    @ApplicationScope
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences("LocationDataStore", Context.MODE_PRIVATE)

    @Provides
    @ApplicationScope
    fun provideLocationDataStore(sharedPreferences: SharedPreferences) = LocationDataStore(sharedPreferences)

    @Provides
    @ApplicationScope
    fun provideLocationRepository(dataStore: LocationDataStore) = LocationRepository(dataStore)

}