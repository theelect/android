package com.tonyecoleelection.android.views.decorators

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class SpacingItemDecoration(private val horizontalSpacing: Int, private val verticalSpacing: Int,
                            private val doubleFirstItemLeftMargin: Boolean = true,
                            private val halfLeftMargin: Boolean = false) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent.getChildAdapterPosition(view) == 0 && doubleFirstItemLeftMargin) {
            outRect.left = horizontalSpacing * 2
        } else {
            outRect.left = horizontalSpacing
        }

        if (halfLeftMargin) {
            outRect.left = horizontalSpacing / 2
        }

        outRect.right = this.horizontalSpacing / 2
        outRect.top = this.verticalSpacing / 2
        outRect.bottom = this.verticalSpacing / 2

    }

}