package com.electionapp.android.utils.common

import android.arch.lifecycle.Observer


internal class NotNullObserver<T>(private val block: (T) -> Unit) : Observer<T> {

  override fun onChanged(it: T?) {
    if (it != null) {
      block(it)
    }
  }
}
