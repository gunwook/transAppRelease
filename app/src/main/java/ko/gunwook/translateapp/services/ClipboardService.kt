package ko.gunwook.translateapp.services

import android.app.Service
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.IBinder
import ko.gunwook.translateapp.db.DatabaseHelper
import org.koin.core.KoinComponent
import org.koin.core.inject
import androidx.core.content.ContextCompat.getSystemService
import android.app.NotificationManager
import android.app.NotificationChannel
import android.os.Build
import ko.gunwook.translateapp.utils.NotificationUtils


class ClipboardService : Service() , ClipboardManager.OnPrimaryClipChangedListener , KoinComponent {

    private val context : Context by inject()

    lateinit var mManager : ClipboardManager

    override fun onCreate() {
        super.onCreate()
        instance = this

        NotificationUtils.startForeground(this)
        mManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        mManager.addPrimaryClipChangedListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()

        mManager.removePrimaryClipChangedListener(this)
    }


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onPrimaryClipChanged() {
        mManager.primaryClip?.let {
            val dataCount = it.getItemCount()
            for (i in 0 until dataCount) {
                DatabaseHelper().saveDBWord(it.getItemAt(i).coerceToText(this).toString(),true)
            }
        }
    }

    fun removeService() {
        stopForeground(true)
        stopSelf()
    }

    companion object {
        var instance : ClipboardService? = null
    }


}