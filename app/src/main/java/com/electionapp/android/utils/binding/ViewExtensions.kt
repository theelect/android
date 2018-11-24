package com.electionapp.android.utils.binding

import android.databinding.BindingAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import android.widget.ViewFlipper
import com.electionapp.android.R
import com.electionapp.android.ui.adapters.base.BindableAdapter
import com.electionapp.android.ui.adapters.base.BindableItemClickListener
import com.electionapp.android.ui.adapters.base.DataBindingAdapter
import com.electionapp.android.utils.extensions.itemDecorations
import com.electionapp.android.views.decorators.SpacingItemDecoration
import org.jetbrains.anko.textColor


@set:BindingAdapter("visible")
var View.visible
    get() = visibility == View.VISIBLE
    set(visible) {
        visibility = if (visible) View.VISIBLE else View.GONE
    }

@set:BindingAdapter("invisible")
var View.invisible
    get() = visibility == View.INVISIBLE
    set(invisible) {
        visibility = if (invisible) View.INVISIBLE else View.VISIBLE
    }

@set:BindingAdapter("gone")
var View.gone
    get() = visibility == View.GONE
    set(gone) {
        visibility = if (gone) View.GONE else View.VISIBLE
    }

@BindingAdapter("data")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, data: T?) {
    if (recyclerView.adapter is BindableAdapter<*> && data != null) {
        (recyclerView.adapter as BindableAdapter<T>).setData(data)
    }
}

@BindingAdapter("data")
fun <T> setViewPagerProperties(viewPager: ViewPager, data: T?) {
    if (viewPager.adapter is BindableAdapter<*> && data != null) {
        (viewPager.adapter as BindableAdapter<T>).setData(data)
    }
}

@BindingAdapter("clickListener")
fun <T> setRecyclerViewClickListener(recyclerView: RecyclerView, clickListener: BindableItemClickListener<T>) {
    if (recyclerView.adapter is DataBindingAdapter<*>) {
        (recyclerView.adapter as DataBindingAdapter<T>).itemClickListener = clickListener
    }
}

@BindingAdapter("verticalList")
fun setRecyclerViewOrientation(recyclerView: RecyclerView, verticalList: Boolean) {
    if (recyclerView.itemDecorationCount > 0) {
        recyclerView.removeItemDecorationAt(0)
    }

    if (verticalList) {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, RecyclerView.VERTICAL, false)
        recyclerView.itemDecorations += SpacingItemDecoration(16, 48, false)
    } else {
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, RecyclerView.HORIZONTAL, false)
        recyclerView.itemDecorations += SpacingItemDecoration(16, 16, false)
    }
}


@BindingAdapter("displayedChild")
fun setDisplayedChild(viewFlipper: ViewFlipper, displayedChild: Int) {
    viewFlipper.displayedChild = displayedChild
}

@BindingAdapter("status")
fun setTextAndColorForStatus(textView: TextView, is_verified:Boolean) {
    if(is_verified){
        textView.text = "VERIFIED"
        textView.textColor = R.color.mds_green_400
    }else{
        textView.text = "INVALID"
        textView.textColor = R.color.mds_red_400
    }
}
