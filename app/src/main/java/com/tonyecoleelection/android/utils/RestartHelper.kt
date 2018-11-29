package com.tonyecoleelection.android.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import javax.inject.Inject


/**
 * Created by aliumujib on 28/03/2018.
 */

interface IRestartHelper {
    fun doRestart()
}

class RestartHelper @Inject constructor(var context: Context) : IRestartHelper {

    override fun doRestart() {
        try {
            //check if the context is given
            //fetch the packagemanager so we can get the default launch activity
            // (you can replace this intent with any other activity if you want
            val pm = context.packageManager
            //check if we got the PackageManager
            if (pm != null) {
                //create the intent with the default start activity for your application
                val mStartActivity = pm.getLaunchIntentForPackage(
                        context.packageName
                )
                if (mStartActivity != null) {
                    mStartActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    //create a pending intent so the application is restarted after System.exit(0) was called.
                    // We use an AlarmManager to call this intent in 100ms
                    val mPendingIntentId = 223344
                    val mPendingIntent = PendingIntent
                            .getActivity(context, mPendingIntentId, mStartActivity,
                                    PendingIntent.FLAG_CANCEL_CURRENT)
                    val mgr = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent)
                    //kill the application
                    System.exit(0)
                } else {
                    Log.e(TAG, "Was not able to restart application, mStartActivity null")
                }
            } else {
                Log.e(TAG, "Was not able to restart application, PM null")
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Was not able to restart application")
        }

    }

    companion object {

        var TAG = RestartHelper::class.java.canonicalName
    }

}
