package ko.gunwook.translateapp.utils

import android.content.Context
import android.content.Intent
import android.os.Build
import ko.gunwook.translateapp.BaseApplication
import ko.gunwook.translateapp.services.ClipboardService
import org.koin.core.KoinComponent
import org.koin.core.inject

object ServiceUtils {
    fun startClipBoardService(mContext : Context){
        if (BaseApplication.clipboardService == null) return

        if (Build.VERSION.SDK_INT >= 26) {
            if (!CommonUtils.isServiceRunning(mContext)) mContext.startForegroundService(BaseApplication.clipboardService)
        } else {
            mContext.startService(BaseApplication.clipboardService)
        }
    }

    fun stopClipBoardService(){
        ClipboardService.instance?.removeService()
    }
}