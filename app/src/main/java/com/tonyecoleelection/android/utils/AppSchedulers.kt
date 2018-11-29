package com.tonyecoleelection.android.utils


import com.tonyecoleelection.domain.base.Schedulers
import io.reactivex.Scheduler

/**
 * Created by aliumujib on 10/06/2018.
 */


class AppSchedulers : Schedulers {

    override val subscribeOn: Scheduler
        get() = io.reactivex.schedulers.Schedulers.io()

    override val observeOn: Scheduler
        get() = io.reactivex.android.schedulers.AndroidSchedulers.mainThread()
}
