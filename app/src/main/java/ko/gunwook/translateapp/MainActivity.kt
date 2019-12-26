package ko.gunwook.translateapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.mattcarroll.hover.overlay.OverlayPermission
import ko.gunwook.translateapp.model.MessageEvent
import ko.gunwook.translateapp.services.ClipboardService
import ko.gunwook.translateapp.services.TransManagerService
import ko.gunwook.translateapp.ui.adapter.MainPagerFragmentAdatper
import ko.gunwook.translateapp.utils.PreferenceHelper
import ko.gunwook.translateapp.utils.ServiceUtils
import kotlinx.android.synthetic.main.bottom_sheet.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainActivity : AppCompatActivity() , KoinComponent{

    val mContext : Context by inject()

    private val REQUEST_CODE_HOVER_PERMISSION = 1000

    private var mPermissionsRequested = false
    private val adapter by lazy { MainPagerFragmentAdatper(this@MainActivity) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewpager.adapter = adapter

        recentView.setOnClickListener {
            viewpager.currentItem = 0
        }

        settingView.setOnClickListener {
            viewpager.currentItem = 1
        }
    }
    override fun onStart() {
        super.onStart()

        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()

        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event : MessageEvent){
        adapter.recentFragment.initData()
    }


    override fun onResume() {
        super.onResume()

        if (!mPermissionsRequested && !OverlayPermission.hasRuntimePermissionToDrawOverlay(this)) {
            @SuppressWarnings("NewApi")
            val myIntent = OverlayPermission.createIntentToRequestOverlayPermission(this)
            startActivityForResult(myIntent, REQUEST_CODE_HOVER_PERMISSION)
        }else {
            startService()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (REQUEST_CODE_HOVER_PERMISSION == requestCode) {
            mPermissionsRequested = true
            startService()
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    fun startService(){
        startService(Intent(this@MainActivity, TransManagerService::class.java))
        if (PreferenceHelper.getInstance(this@MainActivity).getBoolean(PreferenceHelper.SHARED_PREFS_KEY_TEXT_COPY , true)) {
            ServiceUtils.startClipBoardService(mContext)
        }
    }
}
