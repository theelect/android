package com.tonyecoleelection.android.utils.extensions

import android.support.annotation.StringRes
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar


internal fun CoordinatorLayout.snack(@StringRes resId: Int, duration: Int = Snackbar.LENGTH_SHORT, block: Snackbar.() -> Unit = { }) {
  val snack = Snackbar.make(this, resId, duration)
  snack.block()
  snack.show()
}

internal fun CoordinatorLayout.snack(text: CharSequence, duration: Int = Snackbar.LENGTH_SHORT, block: Snackbar.() -> Unit = { }) {
  val snack = Snackbar.make(this, text, duration)
  snack.block()
  snack.show()
}
