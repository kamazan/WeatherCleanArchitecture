package com.kris.weather.domain.interactor.core

import com.kris.weather.domain.functional.Error
import com.kris.weather.domain.functional.ErrorException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import com.kris.weather.domain.functional.Result
import java.lang.Exception

abstract class BaseInteractor<T, in Params> {
    protected abstract fun run(params: Params): T

    operator fun invoke(scope: CoroutineScope, params: Params, onResult: (Result<T>) -> Unit = {}) {
        val job = CoroutineScope(scope.coroutineContext + Dispatchers.IO).async {
            try {
                val result = run(params)
                Result.Success(result)
            } catch (e: ErrorException) {
                Result.Failure<T>(e.error)
            } catch (e: Exception) {
                Result.Failure<T>(Error.Unknown(e))
            }
        }
        scope.launch { onResult(job.await()) }
    }
}