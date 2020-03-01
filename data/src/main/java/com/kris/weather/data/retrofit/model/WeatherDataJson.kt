package com.kris.weather.data.retrofit.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class WeatherDataJson(
    val dt: Date,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds?,
    val wind: Wind?,
    val rain: Rain?,
    val snow: Snow?
)

data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val sea_level: Int,
    val grnd_level: Int,
    val humidity: Int
)

data class Weather(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
)

data class Clouds(
    val all: Int
)

data class Wind(
    val speed: Double,
    val deg: Int
)

data class Rain(
    @SerializedName("3h")
    val rain_volume: Double
)

data class Snow(
    @SerializedName("3h")
    val snow_volume: Double
)