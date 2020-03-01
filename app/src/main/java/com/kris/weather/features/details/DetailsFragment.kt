package com.kris.weather.features.details

import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.kris.weather.R
import com.kris.weather.core.BaseDataBindingFragment
import com.kris.weather.databinding.FragmentDetailsBinding
import com.kris.weather.domain.entity.Weather
import com.kris.weather.utils.MarginSpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_details.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailsFragment: BaseDataBindingFragment<DetailsViewModel, FragmentDetailsBinding>() {
    override val viewModel: DetailsViewModel by viewModel {
        DetailsFragmentArgs.fromBundle(requireArguments()).run {
            parametersOf(weather, location)
        }
    }
    override val layoutId: Int = R.layout.fragment_details

    private val adapter = WeatherFieldAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewWeatherFields.adapter = adapter
        recyclerViewWeatherFields.addItemDecoration(
            MarginSpaceItemDecoration(
                resources,
                R.dimen.keyline_1
            )
        )
        adapter.submitList(viewModel.weather.fields)

        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    private val Weather.fields: List<WeatherField>
        get() {
            return listOfNotNull(
                buildField(temperature, R.drawable.ic_temperature, R.string.weather_perceived_temperature_description, R.string.weather_perceived_temperature),
                buildField(humidity, R.drawable.ic_humidity, R.string.weather_humidity_description, R.string.weather_humidity),
                buildField(windSpeed, R.drawable.ic_wind, R.string.weather_wind_speed_description, R.string.weather_wind_speed),
                buildField(snowVolume, R.drawable.ic_snow, R.string.weather_snow_volume_description, R.string.weather_snow_volume),
                buildField(rainVolume, R.drawable.ic_umbrella, R.string.weather_rain_volume_description, R.string.weather_rain_volume),
                buildField(cloudiness, R.drawable.ic_cloud, R.string.weather_cloudiness_description, R.string.weather_cloudiness),
                buildField(pressure, R.drawable.ic_pressure, R.string.weather_pressure_description, R.string.weather_pressure)
            )
        }


    private fun buildField(value: Number?, @DrawableRes icon: Int, @StringRes description: Int, @StringRes text: Int): WeatherField? {
        return value?.run { WeatherField(icon, description, requireContext().getString(text, this)) }
    }
}
