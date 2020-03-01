package com.kris.weather.features.details

import androidx.recyclerview.widget.DiffUtil
import com.kris.weather.utils.DataBindingAdapter

class WeatherFieldAdapter: DataBindingAdapter<WeatherField>(DiffCallback()) {

    override fun getItemViewType(position: Int): Int = com.kris.weather.R.layout.layout_row_field

    class DiffCallback: DiffUtil.ItemCallback<WeatherField>() {
        override fun areContentsTheSame(oldItem: WeatherField, newItem: WeatherField): Boolean = oldItem.text == newItem.text
        override fun areItemsTheSame(oldItem: WeatherField, newItem: WeatherField): Boolean = oldItem.icon == newItem.icon && oldItem.description == newItem.description
    }
}