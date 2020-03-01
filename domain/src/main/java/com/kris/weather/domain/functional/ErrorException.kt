package com.kris.weather.domain.functional

import java.lang.Exception

class ErrorException(
    val error: Error,
    cause: Throwable? = null,
    message: String = ""
) : Exception(message, cause)