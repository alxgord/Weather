package com.example.artto.weather.data.network.weather.response.forecast

import com.fasterxml.jackson.annotation.JsonProperty

data class ForecastWeatherResponse(

	@JsonProperty("city")
	val city: City?,

	@JsonProperty("cod")
	val cod: String,

	@JsonProperty("list")
	val list: List<ListItem>

)