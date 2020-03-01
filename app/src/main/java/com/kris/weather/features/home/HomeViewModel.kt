package com.kris.weather.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.kris.weather.core.BaseViewModel
import com.kris.weather.domain.entity.Location
import com.kris.weather.domain.entity.Weather
import com.kris.weather.domain.functional.Error
import com.kris.weather.domain.interactor.FetchWeatherData

class HomeViewModel(
    private val fetchWeatherData: FetchWeatherData
) : BaseViewModel() {

    val state: MutableLiveData<State> = MutableLiveData()

    sealed class State {
        object Loading: State()
        object Completed: State()
        class LoadingError(val error: Error): State()
    }

    companion object {
        private const val LOCATION = "Paris"
    }

    val location: MutableLiveData<Location> = MutableLiveData()

    val weatherObjects: MutableLiveData<List<Weather>> = MutableLiveData()

    val nearestWeather: LiveData<Weather> = Transformations.map(weatherObjects) {
        it.firstOrNull()
    }

    fun refresh() {
        state.value = State.Loading
        fetchWeatherData(viewModelScope, LOCATION) { result ->
            result.getOrNull()?.let {
                location.value = it.location
                weatherObjects.value = it.weatherObjects
            }
            state.value = if (result.isFailure) State.LoadingError(result.getErrorOrNull()!!) else State.Completed
        }
    }
}