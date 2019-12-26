package ko.gunwook.translateapp

import android.app.Application
import android.content.Intent
import ko.gunwook.translateapp.modules.network.GoogleModule
import ko.gunwook.translateapp.services.ClipboardService
import ko.gunwook.translateapp.utils.CommonUtils
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class BaseApplication : Application() {

    private val modules = module {
        single { GoogleModule() }
    }


    override fun onCreate() {
        super.onCreate()

        clipboardService = Intent(this, ClipboardService::class.java)
        DEBUG = CommonUtils.isDebuggable(this)

        startKoin{
            androidLogger()
            androidContext(this@BaseApplication)
            modules(modules)
        }
    }

    companion object {
        var clipboardService : Intent? = null
        var DEBUG = false
    }
}