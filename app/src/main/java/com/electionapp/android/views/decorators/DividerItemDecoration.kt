package com.electionapp.android.views.decorators

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import com.electionapp.android.R


class DividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val horizontalDivider: Drawable = context.resources.getDrawable(R.drawable.line_divider)
    private val verticalDivider: Drawable = context.resources.getDrawable(R.drawable.line_divider)

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val childCount = parent.childCount

        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            //HORIZONTAL
            if (i % 4 == 1) {
                val top = child.bottom + params.bottomMargin
                val bottom = top + horizontalDivider.intrinsicHeight
                val left = child.right
                val right = child.right + child.width

                horizontalDivider.setBounds(left, top, right, bottom)
                horizontalDivider.draw(c)
            }else if(i % 4 == 2){
                val top = child.bottom + params.bottomMargin
                val bottom = top + horizontalDivider.intrinsicHeight
                val left = child.left
                val right = child.right + child.width

                horizontalDivider.setBounds(left, top, right, bottom)
                horizontalDivider.draw(c)
            }
            else if(i % 4 == 3 || i % 4 == 0){
                val top = child.bottom + params.bottomMargin
                val bottom = top + horizontalDivider.intrinsicHeight
                val left = child.left
                val right = child.left + child.width

                horizontalDivider.setBounds(left, top, right, bottom)
                horizontalDivider.draw(c)
            }



        }
    }
}