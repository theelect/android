package com.tonyecoleelection.domain.base

import io.reactivex.Scheduler

interface Schedulers {

    val subscribeOn: Scheduler

    val observeOn: Scheduler

}
