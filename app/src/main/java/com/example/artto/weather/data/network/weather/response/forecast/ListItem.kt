package com.example.artto.weather.data.network.weather.response.forecast

import com.fasterxml.jackson.annotation.JsonProperty

data class ListItem(

	@JsonProperty("dt_txt")
	val dtTxt: String?,

	@JsonProperty("weather")
	val weather: List<WeatherItem>,

	@JsonProperty("main")
	val main: Main

)