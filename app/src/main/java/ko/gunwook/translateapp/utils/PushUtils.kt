package ko.gunwook.translateapp.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import io.mattcarroll.hover.R

object PushUtils{

    fun notificationNotify(context : Context){
        val notificationBuilder : NotificationCompat.Builder
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= 26){
            NotificationChannel("transApp" , "transApp" , NotificationManager.IMPORTANCE_DEFAULT).let {
                notificationManager.createNotificationChannel(it)
                notificationBuilder = NotificationCompat.Builder(context,it.id)
            }
        }else {
            notificationBuilder = NotificationCompat.Builder(context,"transApp")
        }


        notificationBuilder.setSmallIcon(R.drawable.tab_background)
            .setContentTitle("Trans App")
            .setContentText("Trans is running")
            .build();
    }

}