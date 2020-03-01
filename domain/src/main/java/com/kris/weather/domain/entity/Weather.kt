package com.kris.weather.domain.entity

import java.io.Serializable
import java.util.*

data class Weather(
    val date: Date,
    val temperature: Int,
    val perceivedTemperature: Int,
    val minTemperature: Int,
    val maxTemperature: Int,
    val pressure: Int,
    val seaLevelPressure: Int,
    val groundLevelPressure: Int,
    val humidity: Int,
    val description: String?,
    val icon: WeatherIcon?,
    val cloudiness: Int? = null,
    private val windSpeedMps: Double? = null,
    val windBearing: Int? = null,
    val rainVolume: Double? = null,
    val snowVolume: Double? = null
): Serializable {
    val windSpeed: Double?
        get() = windSpeedMps?.run { "%.2f".format(this * 60 * 60 / 1000).toDouble() }
}


enum class WeatherIcon {
    CLEAR_DAY,
    CLEAR_NIGHT,
    PARTLY_CLOUDY_DAY,
    PARTLY_CLOUDY_NIGHT,
    CLOUDS_DAY,
    CLOUDS_NIGHT,
    BROKEN_CLOUDS_DAY,
    BROKEN_CLOUDS_NIGHT,
    SHOWER_RAIN_DAY,
    SHOWER_RAIN_NIGHT,
    RAIN_DAY,
    RAIN_NIGHT,
    THUNDERSTORM_DAY,
    THUNDERSTORM_NIGHT,
    SNOW_DAY,
    SNOW_NIGHT,
    MIST_DAY,
    MIST_NIGHT
    ;
}