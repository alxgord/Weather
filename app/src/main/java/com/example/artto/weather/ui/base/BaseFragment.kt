package com.example.artto.weather.ui.base

import com.arellomobile.mvp.MvpAppCompatFragment

abstract class BaseFragment : MvpAppCompatFragment(), BaseMvpView {

    protected val router: BaseRouter
        get() = (parentFragment ?: activity) as BaseRouter

}