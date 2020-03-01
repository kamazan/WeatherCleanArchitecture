package com.kris.weather.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.kris.weather.R
import com.kris.weather.domain.entity.WeatherIcon
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("weatherIcon")
fun ImageView.setWeatherIcon(weatherIcon: WeatherIcon?) {
    val drawable = weatherIcon?.run { ContextCompat.getDrawable(context, drawableRes) }
    setImageDrawable(drawable)
}

@BindingAdapter("date", "format")
fun TextView.setDate(date: Date?, format: String) {
    text = date?.run { SimpleDateFormat(format, Locale.getDefault()).format(this) }
}

@ExperimentalStdlibApi
@BindingAdapter("capitalizedText")
fun TextView.setCapitalizedText(text: String?) {
    this.text = text?.capitalize(Locale.getDefault())
}

@BindingAdapter("data", "text")
fun<T> TextView.setFormattedText(data: Any?, text: String) {
    this.text = data?.run { text } ?: context.getString(R.string.empty_placeholder)
}

@BindingAdapter("srcRes")
fun ImageView.setImageRes(drawableRes: Int) {
    setImageResource(drawableRes)
}