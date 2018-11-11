package com.example.artto.weather.ui.forecast

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.example.artto.weather.data.network.weather.WeatherApiConstants
import com.example.artto.weather.data.network.weather.response.forecast.ListItem
import kotlinx.android.synthetic.main.item_forecast.view.*

class ForecastDayViewHolder(itemView: View, private val viewPool: RecyclerView.RecycledViewPool) :
    RecyclerView.ViewHolder(itemView),
    ForecastDayAdapterContract.ItemView {

    override fun setData(data: Pair<String, List<ListItem>>) {
        itemView.text_view_item_forecast_date_time.text = data.first
        with(itemView.recycler_view_item_forecast) {
            layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = ForecastWeatherAdapter(data)
            adapter?.notifyDataSetChanged()
            setRecycledViewPool(viewPool)
        }
    }

}