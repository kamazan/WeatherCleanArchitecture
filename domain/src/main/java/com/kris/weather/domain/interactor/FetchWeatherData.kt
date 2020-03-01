package com.kris.weather.domain.interactor

import com.kris.weather.domain.entity.WeatherData
import com.kris.weather.domain.interactor.core.BaseInteractor
import com.kris.weather.domain.repository.WeatherRepository

class FetchWeatherData(
    private val weatherRepository: WeatherRepository
): BaseInteractor<WeatherData, String>() {

    override fun run(params: String): WeatherData = weatherRepository.getWeatherData(params)
}