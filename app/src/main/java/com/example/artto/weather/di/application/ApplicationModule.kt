package com.example.artto.weather.di.application

import android.content.Context
import com.example.artto.weather.ApplicationLoader
import com.example.artto.weather.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: ApplicationLoader) {

    @Provides
    @ApplicationScope
    fun context(): Context = application
}