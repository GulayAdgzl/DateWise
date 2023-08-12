package com.example.datewise.ui.notification


import androidx.work.Worker
import androidx.work.WorkerParameters
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.graphics.Color.RED
import android.media.AudioAttributes
import android.media.AudioAttributes.CONTENT_TYPE_SONIFICATION
import android.media.AudioAttributes.USAGE_NOTIFICATION_RINGTONE
import android.media.RingtoneManager.TYPE_NOTIFICATION
import android.media.RingtoneManager.getDefaultUri
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.O
import android.os.Build.VERSION_CODES.S
import android.provider.Settings.Global.getString
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.DEFAULT_ALL
import androidx.core.app.NotificationCompat.PRIORITY_MAX
import androidx.work.ListenableWorker.Result.success
import com.example.datewise.R
import com.example.datewise.data.local.extension.vectorToBitmap
import com.example.datewise.ui.dayEkle.DayEkleFragment

class NotificationWorker(appContext: Context,workerParams:WorkerParameters):
    Worker(appContext,workerParams) {


    override fun doWork(): Result {
        val id = inputData.getLong(NOTIFICATION_ID, 0).toInt()
        sendNotification(id)

        return success()
        //notOlustur()
        //return Result.success()
    }
    @SuppressLint("UnspecifiedImmutableFlag")
    private fun sendNotification(id: Int) {
        val intent = Intent(applicationContext, DayEkleFragment::class.java)
        intent.flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra(NOTIFICATION_ID, id)

        val notificationManager =
            applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val bitmap = applicationContext.vectorToBitmap(R.drawable.black)

        val titleNotification = applicationContext.getString(R.string.notification_title)
        val subtitleNotification = applicationContext.getString(R.string.notification_subtitle)
        val pendingIntent = if (SDK_INT >= S) {
            getActivity(applicationContext, 0, intent, PendingIntent.FLAG_MUTABLE)
        } else {
            getActivity(applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        val notification = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL)
            .setLargeIcon(bitmap).setSmallIcon(R.drawable.white)
            .setContentTitle(titleNotification).setContentText(subtitleNotification)
            .setDefaults(DEFAULT_ALL).setContentIntent(pendingIntent).setAutoCancel(true)

        notification.priority = PRIORITY_MAX

        if (SDK_INT >= O) {
            notification.setChannelId(NOTIFICATION_CHANNEL)

            val ringtoneManager = getDefaultUri(TYPE_NOTIFICATION)
            val audioAttributes = AudioAttributes.Builder().setUsage(USAGE_NOTIFICATION_RINGTONE)
                .setContentType(CONTENT_TYPE_SONIFICATION).build()

            val channel =
                NotificationChannel(NOTIFICATION_CHANNEL, NOTIFICATION_NAME, IMPORTANCE_HIGH)

            channel.enableLights(true)
            channel.lightColor = RED
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            channel.setSound(ringtoneManager, audioAttributes)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(id, notification.build())
    }

    companion object {
        const val NOTIFICATION_ID = "appName_notification_id"
        const val NOTIFICATION_NAME = "appName"
        const val NOTIFICATION_CHANNEL = "appName_channel_01"
        const val NOTIFICATION_WORK = "appName_notification_work"
    }
   /*private fun notOlustur(){
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
            builder.setContentTitle("DateWise").setContentText("Arkadaşımın En Önemli Günü")
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
    }*/


}