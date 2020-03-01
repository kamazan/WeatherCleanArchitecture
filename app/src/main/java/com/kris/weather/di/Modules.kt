package com.kris.weather.di

import com.kris.weather.domain.entity.Location
import com.kris.weather.domain.entity.Weather
import com.kris.weather.features.details.DetailsViewModel
import com.kris.weather.features.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { (weather: Weather, location: Location?) -> DetailsViewModel(weather, location) }

}