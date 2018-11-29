package com.tonyecoleelection.android.utils.common

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.support.annotation.MainThread
import android.util.Log
import java.util.concurrent.atomic.AtomicBoolean

internal class SingleLiveData<T> : MutableLiveData<T>() {

  private val pending = AtomicBoolean(false)

  @MainThread
  override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
    if (hasActiveObservers()) {
      Log.w("SingleLiveData", "Multiple observers registered but only one will be notified of changes.")
    }

    super.observe(owner, Observer<T> { it ->
      if (pending.compareAndSet(true, false)) {
        observer.onChanged(it)
      }
    })
  }

  @MainThread
  override fun setValue(it: T?) {
    pending.set(true)
    super.setValue(it)
  }

  @MainThread
  operator fun invoke() {
    value = null
  }
}
