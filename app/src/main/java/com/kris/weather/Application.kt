package com.kris.weather

import android.app.Application
import com.kris.weather.data.di.apiModule
import com.kris.weather.data.di.repositoryModule
import com.kris.weather.di.viewModelModule
import com.kris.weather.domain.di.interactorModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            modules(
                listOf(
                    apiModule,
                    repositoryModule,
                    interactorModule,
                    viewModelModule
                )
            )
        }
    }
}