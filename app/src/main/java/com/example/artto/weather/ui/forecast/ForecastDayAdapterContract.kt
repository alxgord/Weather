package com.example.artto.weather.ui.forecast

import com.example.artto.weather.data.network.weather.response.forecast.ListItem

interface ForecastDayAdapterContract {

    interface AdapterPresenter {
        fun getDayItemCount(): Int
        fun onBindDayView(view: ItemView, position: Int)
    }

    interface ItemView {
        fun setData(data: Pair<String, List<ListItem>>)
    }
}