package com.example.artto.weather.ui.forecast

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.artto.weather.R
import com.example.artto.weather.data.network.weather.response.forecast.ListItem

class ForecastWeatherAdapter(private val data: Pair<String, List<ListItem>>) :
    RecyclerView.Adapter<ForecastWeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, itemType: Int): ForecastWeatherViewHolder =
        ForecastWeatherViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false))

    override fun getItemCount(): Int = data.second.size

    override fun onBindViewHolder(viewHolder: ForecastWeatherViewHolder, position: Int) = with(data.second[position]) {
        viewHolder.setImage(weather.first().icon)
        viewHolder.setTemp(main.temp)
        viewHolder.setTime(dtTxt?.substringAfterLast(" ")?.substringBeforeLast(":") ?: "")
    }

}