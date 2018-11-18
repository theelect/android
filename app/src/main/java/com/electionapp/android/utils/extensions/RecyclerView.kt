package com.electionapp.android.utils.extensions

import android.support.v7.widget.RecyclerView

internal val RecyclerView.itemDecorations: ItemDecorations get() = ItemDecorations(this)

internal class ItemDecorations(private val view: RecyclerView) {

  operator fun plusAssign(decoration: RecyclerView.ItemDecoration) = view.addItemDecoration(decoration)

}
