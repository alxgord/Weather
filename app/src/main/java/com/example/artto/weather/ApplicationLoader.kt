package com.example.artto.weather

import android.app.Application
import com.example.artto.weather.di.application.ApplicationComponent
import com.example.artto.weather.di.application.ApplicationModule
import com.example.artto.weather.di.application.DaggerApplicationComponent
import com.facebook.stetho.Stetho

class ApplicationLoader : Application() {

    companion object {

        private lateinit var application: ApplicationLoader

        val applicationComponent
            get() = application.applicationComponent

    }

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        application = this
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()

        Stetho.initializeWithDefaults(this)
    }

}