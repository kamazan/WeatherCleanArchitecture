package com.kris.weather.data.retrofit

import android.content.Context
import com.google.gson.GsonBuilder
import com.kris.weather.data.retrofit.adapter.DateTypeAdapter
import com.kris.weather.data.retrofit.interceptor.NetworkConnectionInterceptor
import com.kris.weather.data.retrofit.interceptor.OpenWeatherTokenInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class RetrofitClient(
    context: Context,
    baseUrl: String
) {

    private val retrofit: Retrofit

    init {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(OpenWeatherTokenInterceptor)
            .addInterceptor(NetworkConnectionInterceptor(context))
            .readTimeout(1, TimeUnit.MINUTES).build()

        val gson = GsonBuilder()
            .registerTypeAdapter(Date::class.java, DateTypeAdapter)
            .create()

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }


    fun <T> getService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }
}