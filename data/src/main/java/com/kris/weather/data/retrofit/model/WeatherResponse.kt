package com.kris.weather.data.retrofit.model

data class WeatherResponse(
    val city: City,
    val list: List<WeatherDataJson>
)