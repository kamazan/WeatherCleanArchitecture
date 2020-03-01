package com.kris.weather.data.retrofit.interceptor


import android.content.Context
import android.net.ConnectivityManager
import com.kris.weather.domain.functional.Error
import com.kris.weather.domain.functional.ErrorException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(context: Context): Interceptor {
    private val connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val isConnected: Boolean
        get() = connectivityManager.activeNetworkInfo?.isConnected == true

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected) throw ErrorException(Error.NetworkNotReachable)
        return chain.proceed(chain.request())
    }
}