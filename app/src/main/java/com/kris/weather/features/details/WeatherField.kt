package com.kris.weather.features.details

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class WeatherField(
    @DrawableRes val icon: Int?,
    @StringRes val description: Int,
    val text: String
)