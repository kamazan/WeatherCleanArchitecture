package com.kris.weather.domain.entity

data class WeatherData(
    val location: Location,
    val weatherObjects: List<Weather>
)
