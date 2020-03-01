package com.kris.weather.domain.functional

sealed class Error(val cause: Throwable? = null) {

    class Unknown(cause: Throwable): Error(cause)
    object NetworkNotReachable: Error()

    abstract class CustomError(cause: Throwable? = null): Error(cause)

}