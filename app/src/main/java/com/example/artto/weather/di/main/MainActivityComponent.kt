package com.example.artto.weather.di.main

import com.example.artto.weather.di.scope.ViewScope
import com.example.artto.weather.ui.main.MainPresenter
import dagger.Subcomponent

@Subcomponent(modules = [MainActivityModule::class])
@ViewScope
interface MainActivityComponent {

    fun presenter(): MainPresenter

}