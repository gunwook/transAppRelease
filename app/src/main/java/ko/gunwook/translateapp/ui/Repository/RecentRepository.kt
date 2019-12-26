package ko.gunwook.translateapp.ui.Repository

import ko.gunwook.translateapp.db.DatabaseHelper
import ko.gunwook.translateapp.db.entity.WordEntity
import kotlinx.coroutines.*

class RecentRepository {
    private val databaseHelper : DatabaseHelper by lazy { DatabaseHelper() }

    suspend fun getWordAll()  = withContext(Dispatchers.IO){
         databaseHelper.getWordList()
    }
    suspend fun removeWord(model : WordEntity) = withContext(Dispatchers.IO){
        databaseHelper.removeWord(model)
    }
}