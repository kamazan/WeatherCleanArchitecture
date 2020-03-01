package com.kris.weather.data.retrofit.service

import com.kris.weather.data.retrofit.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {
    companion object {
        private const val FORECAST_PATH = "forecast"

        private const val LOCATION_PARAMETER = "q"
        private const val UNITS_PARAMETER = "units"


    }

    @GET(FORECAST_PATH)
    fun getForecastData(@Query(LOCATION_PARAMETER) cityName: String, @Query(UNITS_PARAMETER) units: String = "metric"): Call<WeatherResponse>
}