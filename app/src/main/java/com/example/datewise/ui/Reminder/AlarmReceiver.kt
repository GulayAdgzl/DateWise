package com.example.datewise.ui.Reminder

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.datewise.R

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationManager =
            context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val reminderName = intent?.getStringExtra("reminder_name") ?: "Hatırlatma"

        // Bildirim oluşturmak için bir NotificationCompat.Builder kullanabilirsiniz
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle("Hatırlatma")
            .setContentText(reminderName)
            .setSmallIcon(R.drawable.bell)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        notificationManager.notify(0, notification)
    }

    companion object {
        private const val CHANNEL_ID = "reminder_channel"
    }
}
