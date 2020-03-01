package com.kris.weather.utils


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.kris.weather.BR

abstract class DataBindingAdapter<T>(diffCallback: DiffUtil.ItemCallback<T>, private val listener: OnItemClickListener<T>? = null) :
    ListAdapter<T, DataBindingViewHolder<T>>(diffCallback) {

    interface OnItemClickListener<T> {
        fun onItemClick(item: T)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        binding.setVariable(BR.listener, listener)
        return onCreateDataBindingViewHolder(binding)
    }

    open fun onCreateDataBindingViewHolder(binding: ViewDataBinding): DataBindingViewHolder<T> = DataBindingViewHolder(binding)


    override fun onBindViewHolder(holder: DataBindingViewHolder<T>, position: Int) = holder.bind(getItem(position))
}