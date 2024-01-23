package com.tde.chapi.UI.Home.Appoinment.AlarmAppoinment

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.tde.chapi.R

const val notificationIDAppointment = 1
const val channelIDAppointment = "channel2"
const val titleExtraAppointment = "titleExtra"
const val messageExtraAppointment = "messageExtra"

class NotificationAppoinment {

    class NotificationAppointment : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {

            val notificationAppointment = NotificationCompat.Builder(context, channelIDAppointment)
                .setSmallIcon(R.drawable.ic_pill)
                .setContentTitle(intent.getStringExtra(titleExtraAppointment))
                .setContentText(intent.getStringExtra(messageExtraAppointment))
                .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
                .build()

            val managerAppoinment = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            managerAppoinment.notify(notificationIDAppointment, notificationAppointment)
        }

    }
}