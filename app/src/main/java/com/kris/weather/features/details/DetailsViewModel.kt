package com.kris.weather.features.details

import com.kris.weather.core.BaseViewModel
import com.kris.weather.domain.entity.Location
import com.kris.weather.domain.entity.Weather

class DetailsViewModel(val weather: Weather, val location: Location?): BaseViewModel()