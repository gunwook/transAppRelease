package ko.gunwook.translateapp.utils

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.app.ActivityManager
import ko.gunwook.translateapp.services.ClipboardService


object CommonUtils {

    fun isDebuggable(context: Context): Boolean {
        var debuggable = false

        val pm = context.packageManager
        try {
            val appinfo = pm.getApplicationInfo(context.getPackageName(), 0)
            debuggable = 0 != appinfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
        } catch (e: PackageManager.NameNotFoundException) {
        }

        return debuggable
    }

    fun isServiceRunning(context: Context?): Boolean {
        if (context == null) return false

        val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
            if (ClipboardService::class.java.getName() == service.service.className)
                return true
        }
        return false
    }
}