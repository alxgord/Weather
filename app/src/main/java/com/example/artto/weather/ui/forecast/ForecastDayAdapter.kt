package com.example.artto.weather.ui.forecast

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.artto.weather.R

class ForecastDayAdapter(private val adapterPresenter: ForecastDayAdapterContract.AdapterPresenter) :
    RecyclerView.Adapter<ForecastDayViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, itemType: Int): ForecastDayViewHolder =
        ForecastDayViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false), viewPool)

    override fun getItemCount(): Int = adapterPresenter.getDayItemCount()

    override fun onBindViewHolder(view: ForecastDayViewHolder, position: Int) =
        adapterPresenter.onBindDayView(view, position)
}