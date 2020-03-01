package com.kris.weather.features.home

import androidx.recyclerview.widget.DiffUtil
import com.kris.weather.R
import com.kris.weather.domain.entity.Weather
import com.kris.weather.utils.DataBindingAdapter

class WeatherAdapter(listener: OnItemClickListener<Weather>? = null): DataBindingAdapter<Weather>(DiffCallback(), listener) {

    override fun getItemViewType(position: Int): Int = R.layout.layout_row_weather

    class DiffCallback: DiffUtil.ItemCallback<Weather>() {
        override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean = oldItem.date == newItem.date

        override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean = oldItem == newItem

    }
}