package com.tonyecoleelection.android.utils.tracker

import android.util.Log
import com.google.android.gms.analytics.Tracker
import com.google.android.gms.analytics.HitBuilders
import javax.inject.Inject


interface IAnalyticsTracker {
    fun recordScreenView(screenName: String)
}

class AnalyticsTracker @Inject constructor(var googleAnalyticsTracker: Tracker) : IAnalyticsTracker {

    private val TAG = javaClass.simpleName


    override fun recordScreenView(screenName: String) {
        Log.d(TAG, screenName)
        googleAnalyticsTracker.setScreenName(screenName)
        googleAnalyticsTracker.send(HitBuilders.ScreenViewBuilder().build())
    }

}