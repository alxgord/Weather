package com.example.artto.weather.data.network.weather.response.forecast

import com.fasterxml.jackson.annotation.JsonProperty

data class City(

    @JsonProperty("country")
    val country: String?,

    @JsonProperty("name")
    val name: String?

)