package com.kris.weather.utils

import android.graphics.Rect
import android.graphics.drawable.InsetDrawable
import android.view.View
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.kris.weather.domain.entity.WeatherIcon
import com.kris.weather.R

val WeatherIcon.drawableRes: Int
    get() {
        return when (this) {
            WeatherIcon.CLEAR_DAY -> R.drawable.ic_weather_clear_day
            WeatherIcon.CLEAR_NIGHT -> R.drawable.ic_weather_clear_night
            WeatherIcon.PARTLY_CLOUDY_DAY -> R.drawable.ic_weather_partly_cloudy_day
            WeatherIcon.PARTLY_CLOUDY_NIGHT -> R.drawable.ic_weather_partly_cloudy_night
            WeatherIcon.CLOUDS_DAY, WeatherIcon.CLOUDS_NIGHT, WeatherIcon.BROKEN_CLOUDS_DAY, WeatherIcon.BROKEN_CLOUDS_NIGHT -> R.drawable.ic_weather_cloudy
            WeatherIcon.SHOWER_RAIN_DAY, WeatherIcon.SHOWER_RAIN_NIGHT, WeatherIcon.RAIN_DAY, WeatherIcon.RAIN_NIGHT -> R.drawable.ic_weather_rain
            WeatherIcon.THUNDERSTORM_DAY, WeatherIcon.THUNDERSTORM_NIGHT -> R.drawable.ic_weather_thunderstorm
            WeatherIcon.SNOW_DAY, WeatherIcon.SNOW_NIGHT -> R.drawable.ic_weather_snow
            WeatherIcon.MIST_DAY, WeatherIcon.MIST_NIGHT -> R.drawable.ic_weather_mist
        }
    }

fun RecyclerView.addVerticalDivider(@DrawableRes drawableRes: Int, @DimenRes marginHorizontal: Int? = null) {
    val margin = marginHorizontal?.run { resources.getDimensionPixelSize(this) } ?: 0
    addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
        setDrawable(
            InsetDrawable(
                ContextCompat.getDrawable(context, drawableRes)!!,
                margin,
                0,
                margin,
                0
            )
        )
    })
}