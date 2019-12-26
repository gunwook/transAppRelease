package ko.gunwook.translateapp.utils

import android.app.*
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import ko.gunwook.translateapp.MainActivity
import ko.gunwook.translateapp.R
import android.media.RingtoneManager
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import ko.gunwook.translateapp.services.ClipboardService


object NotificationUtils {

    fun startForeground(service: ClipboardService) {
        try {
            var channelId = ""
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                channelId = createNotificationChannel(service)
            }

            val notification = getNotification(service, channelId)
            if (notification != null) {
                service.startForeground(1220, notification)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Throws(NullPointerException::class)
    private fun createNotificationChannel(service : ClipboardService): String {
        val channelId = "Cliboard Service"
        val channelName = "Cliboard Service"
        val chan = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)

        (service.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(chan)

        return channelId
    }

    private fun getNotification(paramContext: Context, channelId: String): Notification? {
        val notification = NotificationCompat.Builder(paramContext, channelId)
            .setSmallIcon(R.drawable.book)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setTicker("ClipBoard")
            .build()
        notification.flags = 64
        return notification
    }


    fun createMissedCallNotification( context: Context, title: String, text: String){
        val pendingIntent = PendingIntent.getActivity(context, 0, Intent(context,MainActivity::class.java), PendingIntent.FLAG_UPDATE_CURRENT)
        val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager?
        val builder: NotificationCompat.Builder

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel(context.getString(R.string.app_name), context.getString(R.string.app_name), NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager!!.createNotificationChannel(mChannel)
            builder = NotificationCompat.Builder(context, mChannel.id)
        } else {
            builder = NotificationCompat.Builder(context)
        }
        builder.setAutoCancel(true)
            .setSmallIcon( R.drawable.book)
            .setTicker(title)
            .setContentTitle(title)
            .setContentText(text)
            .setVibrate(longArrayOf(0L))
            .priority = NotificationCompat.PRIORITY_MAX

        builder.setContentIntent(pendingIntent)
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))

        notificationManager!!.notify(1234, builder.build())
    }
}