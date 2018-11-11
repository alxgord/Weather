package com.example.artto.weather.ui.forecast

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.example.artto.weather.R
import com.example.artto.weather.data.network.weather.WeatherApiConstants
import kotlinx.android.synthetic.main.item_weather.view.*

class ForecastWeatherViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    fun setImage(id: String) {
        Glide.with(itemView)
            .load("${WeatherApiConstants.BASE_URL}${WeatherApiConstants.PATH_ICON}$id.png")
            .into(itemView.image_view_item_weather)
    }

    fun setTime(time: String) {
        itemView.text_view_item_weather_time.text = time
    }

    fun setTemp(temp: Float) = with(itemView.text_view_item_weather_temp) {
        text = context.getString(R.string.forecast_item_weather_temp, temp.toInt())
    }
}