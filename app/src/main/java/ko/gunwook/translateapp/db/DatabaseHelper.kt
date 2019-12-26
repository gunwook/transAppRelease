package ko.gunwook.translateapp.db

import android.content.Context
import android.widget.Toast
import ko.gunwook.translateapp.R
import ko.gunwook.translateapp.db.entity.WordEntity
import ko.gunwook.translateapp.modules.network.GoogleModule
import kotlinx.coroutines.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.lang.Exception
import java.util.regex.Pattern
import ko.gunwook.translateapp.model.MessageEvent
import ko.gunwook.translateapp.utils.NotificationUtils
import ko.gunwook.translateapp.utils.PreferenceHelper
import org.greenrobot.eventbus.EventBus


class DatabaseHelper : KoinComponent {
    private val network : GoogleModule by inject()
    private val context : Context by inject()

    fun saveDBWord(value : String , isClipboard : Boolean){

            if (!Pattern.matches("^[a-zA-Z]*$", value)) return

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val data = CoroutineScope(Dispatchers.IO).async {
                        return@async network.getGoogleHelper(context).requestTransWord(context, value)
                    }


                    WordEntity().apply {
                        word = value
                        timestamp = System.currentTimeMillis().toString()
                        transword = data.await() ?: ""

                        AppDatabase.getInstance(context)?.WordDao()?.upsertWord(this)

                        EventBus.getDefault().post(MessageEvent(""))

                        if (isClipboard) {
                            NotificationUtils.createMissedCallNotification(context, word, transword)
                        }
                    }
                }catch (e :Exception){
                    Toast.makeText(context , context.getString(R.string.fail) , Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }
    }

    suspend fun getWordList() : List<WordEntity>? {
        PreferenceHelper.getInstance(context).getInt(PreferenceHelper.SHARED_PREFS_KEY_SEARCH_TYPE, 0).let {
            return when(it){
                0 ->  AppDatabase.getInstance(context)?.WordDao()?.recentSelcetAll(true)
                1 ->  AppDatabase.getInstance(context)?.WordDao()?.countSelcetAll(true)
                else -> AppDatabase.getInstance(context)?.WordDao()?.randomSelectAll(true)
            }
        }
    }

    suspend fun removeWord(model : WordEntity){
        AppDatabase.getInstance(context)?.WordDao()?.deleteById(!model.visibleYn,model.id)
    }
}