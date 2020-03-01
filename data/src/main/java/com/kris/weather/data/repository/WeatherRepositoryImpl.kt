package com.kris.weather.data.repository

import com.kris.weather.data.exception.InvalidWeatherReceived
import com.kris.weather.data.retrofit.RetrofitClient
import com.kris.weather.data.retrofit.model.WeatherDataJson
import com.kris.weather.data.retrofit.service.OpenWeatherService
import com.kris.weather.domain.entity.Location
import com.kris.weather.domain.entity.Weather
import com.kris.weather.domain.entity.WeatherData
import com.kris.weather.domain.entity.WeatherIcon
import com.kris.weather.domain.functional.ErrorException
import com.kris.weather.domain.repository.WeatherRepository
import java.lang.NullPointerException
import kotlin.math.roundToInt

class WeatherRepositoryImpl(
    client: RetrofitClient
) : WeatherRepository {

    private val weatherService = client.getService(OpenWeatherService::class.java)

    override fun getWeatherData(cityName: String): WeatherData {
        val response = weatherService.getForecastData(cityName).execute()
        return try {
            with(response.body()!!) {
                WeatherData(
                    location = city.run { Location(name, country) },
                    weatherObjects = list.map { it.getWeatherData(city.timezone) }
                )
            }
        } catch (e: NullPointerException) {
            throw ErrorException(error = InvalidWeatherReceived)
        }
    }

    private fun WeatherDataJson.getWeatherData(dateOffset: Int): Weather {
        val weatherInfo = weather.first()
        return Weather(
            date = dt.apply { time += dateOffset },
            temperature = main.temp.roundToInt(),
            perceivedTemperature = main.feels_like.roundToInt(),
            minTemperature = main.temp_min.roundToInt(),
            maxTemperature = main.temp_max.roundToInt(),
            pressure = main.pressure,
            seaLevelPressure = main.sea_level,
            groundLevelPressure = main.grnd_level,
            humidity = main.humidity,
            icon = weatherInfo.icon.weatherIcon,
            description = weatherInfo.description,
            cloudiness = clouds?.all,
            windSpeedMps = wind?.speed,
            windBearing = wind?.deg,
            rainVolume = rain?.rain_volume,
            snowVolume = snow?.snow_volume
        )
    }

    private val String.weatherIcon: WeatherIcon?
        get() {
            return when (this) {
                "01d" -> WeatherIcon.CLEAR_DAY
                "01n" -> WeatherIcon.CLEAR_NIGHT
                "02d" -> WeatherIcon.PARTLY_CLOUDY_DAY
                "02n" -> WeatherIcon.PARTLY_CLOUDY_NIGHT
                "03d" -> WeatherIcon.CLOUDS_DAY
                "03n" -> WeatherIcon.CLOUDS_NIGHT
                "04d" -> WeatherIcon.BROKEN_CLOUDS_DAY
                "04n" -> WeatherIcon.BROKEN_CLOUDS_NIGHT
                "09d" -> WeatherIcon.SHOWER_RAIN_DAY
                "09n" -> WeatherIcon.SHOWER_RAIN_NIGHT
                "10d" -> WeatherIcon.RAIN_DAY
                "10n" -> WeatherIcon.RAIN_NIGHT
                "11d" -> WeatherIcon.THUNDERSTORM_DAY
                "11n" -> WeatherIcon.THUNDERSTORM_NIGHT
                "13d" -> WeatherIcon.SNOW_DAY
                "13n" -> WeatherIcon.SNOW_NIGHT
                "50d" -> WeatherIcon.MIST_DAY
                "50n" -> WeatherIcon.MIST_NIGHT
                else -> null
            }
        }
}