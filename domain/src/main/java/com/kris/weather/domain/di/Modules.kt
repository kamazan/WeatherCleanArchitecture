package com.kris.weather.domain.di

import com.kris.weather.domain.interactor.FetchWeatherData
import org.koin.dsl.module

val interactorModule = module {
    factory { FetchWeatherData(get()) }
}