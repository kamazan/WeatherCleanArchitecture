package com.kris.weather.domain.functional

import java.lang.Exception

interface Result<T> {

    fun getOrNull(): T? = null
    fun getErrorOrNull(): Error? = null

    val isSuccess: Boolean
    val isFailure: Boolean

    class Success<T>(private val result: T): Result<T> {
        override val isSuccess: Boolean = true
        override val isFailure: Boolean = false
        override fun getOrNull(): T? = result
    }

    class Failure<T>(private val error: Error): Result<T> {
        override val isSuccess: Boolean = true
        override val isFailure: Boolean = true

        override fun getErrorOrNull(): Error = error
    }
}