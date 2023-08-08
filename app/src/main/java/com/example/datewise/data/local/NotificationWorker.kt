package com.example.datewise.data.local

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.datewise.R
import com.example.datewise.ui.dayEkle.DayEkleFragment

class NotificationWorker(appContext: Context,workerParams:WorkerParameters):
    Worker(appContext,workerParams) {
    override fun doWork(): Result {
        notOlustur()
        return Result.success()
    }
    private fun notOlustur(){
        val builder:NotificationCompat.Builder
        val notificationManager=applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val intent= Intent(applicationContext, DayEkleFragment::class.java)
        val contentToGo=PendingIntent.getActivity(applicationContext,1,intent,PendingIntent.FLAG_CANCEL_CURRENT)
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val channelId="channelId"
            val channelIntroduction="channelIntroduction"
            val channelName="channelName"
            val channelPriority=NotificationManager.IMPORTANCE_HIGH
            var channel:NotificationChannel?=notificationManager.getNotificationChannel(channelId)
            if(channel==null){
                channel=NotificationChannel(channelId,channelName,channelPriority)
                channel.description=channelIntroduction
                notificationManager.createNotificationChannel(channel)
            }
            builder=NotificationCompat.Builder(applicationContext,channelId)
            builder.setContentTitle("DateWise Hatırlatıcı").setContentText("Arkadaşımın En Önemli Günü")
                .setSmallIcon(R.drawable.bell)
                .setAutoCancel(true)
                .setContentIntent(contentToGo)

        }else{
            builder=NotificationCompat.Builder(applicationContext)
            builder.setContentTitle("DateWise Hatırlatıcı").setContentText("Arkadaşımın En Önemli Günü")
                .setSmallIcon(R.drawable.bell)
                .setAutoCancel(true)
                .setContentIntent(contentToGo)
                .priority=Notification.PRIORITY_HIGH
        }
        notificationManager.notify(1,builder.build())
    }

}