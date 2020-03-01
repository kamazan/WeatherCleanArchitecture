package com.kris.weather.domain.repository

import com.kris.weather.domain.entity.WeatherData

interface WeatherRepository {
    fun getWeatherData(cityName: String): WeatherData
}