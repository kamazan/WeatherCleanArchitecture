package com.kris.weather.domain.entity

import java.io.Serializable


data class Location(
    val cityName: String,
    val country: String
): Serializable