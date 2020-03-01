package com.kris.weather.data.di

import com.kris.weather.data.repository.WeatherRepositoryImpl
import com.kris.weather.data.retrofit.RetrofitClient
import com.kris.weather.domain.repository.WeatherRepository
import org.koin.dsl.module

val apiModule = module {
    single { RetrofitClient(get(), "https://api.openweathermap.org/data/2.5/") }
}

val repositoryModule = module {
    factory<WeatherRepository> { WeatherRepositoryImpl(get()) }
}