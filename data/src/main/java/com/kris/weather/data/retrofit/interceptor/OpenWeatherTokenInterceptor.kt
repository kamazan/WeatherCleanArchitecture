package com.kris.weather.data.retrofit.interceptor

import com.kris.weather.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

object OpenWeatherTokenInterceptor : Interceptor {

    private const val API_TOKEN_KEY = "appid"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url =
            request.url().newBuilder().addQueryParameter(API_TOKEN_KEY, BuildConfig.API_KEY).build()
        val newRequest = request.newBuilder().url(url).build();
        return chain.proceed(newRequest)
    }
}