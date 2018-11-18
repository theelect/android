package com.electionapp.android.utils.notifications

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import com.electionapp.android.R
import com.electionapp.android.ui.main.MainActivity


/**
 * Helper class to manage notification channels, and create notifications.
 */
 class NotificationHelper(ctx: Context) : ContextWrapper(ctx) {
    private val manager: NotificationManager by lazy {
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    private val notification: NotificationCompat.Builder
        get() {
            val intent = Intent(this, MainActivity::class.java)

            val builder = NotificationCompat.Builder(this)
                    .setOngoing(true)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .setSmallIcon(R.drawable.email_grey)
                    .setWhen(System.currentTimeMillis())
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                builder.setChannelId(PRIMARY_CHANNEL)
            }

            return builder
        }

    public fun showNotification(title: String, content: String, id: Int) {
        notification.setContentText(title)
                .setContentTitle(content)
                .setTicker(content)
        manager.notify(id, notification.build())
    }

    public fun removeNotification(id:Int){
        manager.cancel(id)
    }

    companion object {
        val PRIMARY_CHANNEL = "default"
        val SECONDARY_CHANNEL = "second"

        val NOTIFICATION_ID_SYNC = 13454

    }
}
