package com.example.artto.weather.data.network.weather.response.forecast

import com.fasterxml.jackson.annotation.JsonProperty

data class Main(

	@JsonProperty("temp")
	val temp: Float

)