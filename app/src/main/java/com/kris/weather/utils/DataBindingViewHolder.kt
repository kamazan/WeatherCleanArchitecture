package com.kris.weather.utils


import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView
import com.kris.weather.BR

open class DataBindingViewHolder<T>(protected val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root), LifecycleOwner {

    private val lifecycleRegistry: LifecycleRegistry = LifecycleRegistry(this)

    init {
        lifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    override fun getLifecycle() = lifecycleRegistry

    fun onAppear() {
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }


    fun onDisappear() {
        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }

    open fun bind(item: T) {
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }
}