package com.example.artto.weather.data.network.weather.response.forecast

import com.fasterxml.jackson.annotation.JsonProperty

data class WeatherItem(

    @JsonProperty("icon")
    val icon: String,

    @JsonProperty("description")
    val description: String,

    @JsonProperty("main")
    val main: String,

    @JsonProperty("id")
    val id: Int
)