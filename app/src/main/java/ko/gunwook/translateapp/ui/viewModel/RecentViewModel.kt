package ko.gunwook.translateapp.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ko.gunwook.translateapp.base.BaseAdapterViewModel
import ko.gunwook.translateapp.db.entity.WordEntity
import ko.gunwook.translateapp.ui.Repository.RecentRepository
import kotlinx.coroutines.*

class RecentViewModel : BaseAdapterViewModel()  {
    private var _words : MutableLiveData<List<WordEntity>>? = MutableLiveData()
    private val recentRepository : RecentRepository by lazy { RecentRepository() }
    private val job = SupervisorJob()
    private val scopes = CoroutineScope(Dispatchers.Main + job)

    val data : LiveData<List<WordEntity>>? get() = _words

    fun initData() {
        scopes.launch {
            _words?.value = recentRepository.getWordAll()
        }
    }


    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun getItem(position: Int): Any? {
        return data?.value?.get(position)
    }

    override fun getItemCount(): Int {
        return  data?.value?.size ?: 0
    }

    fun onDeleteClick(model : WordEntity){
        scopes.launch {
           recentRepository.removeWord(model)

           _words?.value = recentRepository.getWordAll()
        }
    }
}