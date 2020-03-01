package com.kris.weather.features.home

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.kris.weather.R
import com.kris.weather.core.BaseDataBindingFragment
import com.kris.weather.databinding.FragmentHomeBinding
import com.kris.weather.domain.entity.Weather
import com.kris.weather.domain.functional.Error
import com.kris.weather.utils.DataBindingAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel
import com.kris.weather.utils.addVerticalDivider

class HomeFragment: BaseDataBindingFragment<HomeViewModel, FragmentHomeBinding>(), DataBindingAdapter.OnItemClickListener<Weather> {
    override val layoutId: Int = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModel()

    private val adapter: WeatherAdapter = WeatherAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()

        recyclerViewWeather.adapter = adapter
        recyclerViewWeather.addVerticalDivider(R.drawable.list_divider, R.dimen.standard_horizontal_padding)

        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.colorPrimary))

        refresh()
        swipeRefreshLayout.setOnRefreshListener {
            refresh()
        }

    }

    override fun onItemClick(item: Weather) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
            weather = item,
            location = viewModel.location.value
        ))
    }

    private fun observeViewModel() {
        viewModel.weatherObjects.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        viewModel.state.observe(viewLifecycleOwner, Observer {
            swipeRefreshLayout.isRefreshing = it is HomeViewModel.State.Loading
            when (it) {
                is HomeViewModel.State.LoadingError -> {
                    val message = requireContext().getString(when (it.error) {
                        is Error.NetworkNotReachable -> R.string.error_server_not_reachable
                        else -> R.string.error_unknown
                    })

                    showSnackbar(message, requireContext().getString(R.string.error_loading_refresh_action)) {
                        refresh()
                    }
                }
                else -> {
                    dismissSnackbar()
                }
            }
        })
    }

    private fun refresh() {
        viewModel.refresh()
    }
}