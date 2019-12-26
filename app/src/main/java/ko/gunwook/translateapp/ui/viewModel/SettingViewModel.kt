package ko.gunwook.translateapp.ui.viewModel

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ko.gunwook.translateapp.model.MessageEvent
import ko.gunwook.translateapp.services.ClipboardService
import ko.gunwook.translateapp.utils.PreferenceHelper
import ko.gunwook.translateapp.utils.ServiceUtils
import ko.gunwook.translateapp.utils.setValue
import org.greenrobot.eventbus.EventBus

class SettingViewModel(application: Application) : AndroidViewModel(application)  {

    private val mContext : Application = application

    val liveData : MutableLiveData<Boolean> =  MutableLiveData()

    fun sendEvent(index : Int){
       PreferenceHelper.getInstance(mContext).setValue(PreferenceHelper.SHARED_PREFS_KEY_SEARCH_TYPE , index)

       EventBus.getDefault().post(MessageEvent(""))
   }

    fun textCopySetting(type : Boolean){
        PreferenceHelper.getInstance(mContext).let {
            if (type){
                it.setValue(PreferenceHelper.SHARED_PREFS_KEY_TEXT_COPY , true)
                ServiceUtils.startClipBoardService(mContext)
            }else {
                it.setValue(PreferenceHelper.SHARED_PREFS_KEY_TEXT_COPY , false)
                ServiceUtils.stopClipBoardService()
            }
        }
    }

    fun initData(){
        liveData.value = PreferenceHelper.getInstance(mContext).getBoolean(PreferenceHelper.SHARED_PREFS_KEY_TEXT_COPY , true)
    }
}