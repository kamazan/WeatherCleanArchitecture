package com.kris.weather.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.kris.weather.MainActivity
import com.kris.weather.R
import kotlinx.android.synthetic.main.activity_main.*


abstract class BaseFragment : Fragment() {
    abstract val layoutId: Int


    private var snackbar: Snackbar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    protected fun showSnackbar(
        message: String,
        actionText: String? = null,
        action: (() -> Any)? = null
    ) {
        val context = requireContext()
        val container = (activity as? MainActivity)?.layoutRoot
        val snackbar =
            container?.run { Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE) }.apply {
                this?.view?.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.colorPrimaryDark
                    )
                )
            }
        action?.let {
            snackbar?.setActionTextColor(ContextCompat.getColor(context, R.color.onPrimary))
            snackbar?.setAction(actionText) { _ ->
                it.invoke()
                snackbar.dismiss()
            }
        }

        this.snackbar?.dismiss()
        this.snackbar = snackbar
        snackbar?.show()
    }

    protected fun dismissSnackbar() {
        snackbar?.dismiss()
        snackbar = null
    }
}

abstract class BaseDataBindingFragment<ViewModel : androidx.lifecycle.ViewModel, DataBinding : ViewDataBinding> :
    BaseFragment() {

    abstract val viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return DataBindingUtil.inflate<DataBinding>(
            inflater,
            layoutId,
            container,
            false
        ).apply {
            onCreateViewDataBinding(this)
        }.root
    }

    open fun onCreateViewDataBinding(dataBinding: DataBinding) {
        dataBinding.lifecycleOwner = this
        dataBinding.setVariable(com.kris.weather.BR.viewModel, viewModel)
    }

}