package com.kris.weather.utils


import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class MarginSpaceItemDecoration(resources: Resources, @DimenRes height: Int? = null, @DimenRes width: Int? = null) :
    RecyclerView.ItemDecoration() {

    private val verticalMargin: Int = height?.run { resources.getDimension(this).toInt() } ?: 0
    private val horizontalMargin: Int = width?.run { resources.getDimension(this).toInt() } ?: 0

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = verticalMargin
        outRect.left = horizontalMargin
        parent.adapter?.itemCount?.let { count ->
            if (parent.getChildAdapterPosition(view) >= count - 1) {
                outRect.bottom = verticalMargin
                outRect.right = horizontalMargin
            }
        }
    }
}