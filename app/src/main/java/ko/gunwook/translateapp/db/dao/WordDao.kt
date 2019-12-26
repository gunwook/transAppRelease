package ko.gunwook.translateapp.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Maybe
import ko.gunwook.translateapp.db.entity.WordEntity


@Dao
interface WordDao : BaseDao<WordEntity> {

    @Query("SELECT * FROM word WHERE id = :id")
    fun selectById(id: Int): Maybe<WordEntity>

    @Query("SELECT * FROM word WHERE visibleYn = :visibleYn ORDER BY RANDOM()")
    fun randomSelectAll(visibleYn : Boolean): List<WordEntity>

    @Query("SELECT * FROM word WHERE visibleYn = :visibleYn ORDER BY timestamp DESC")
    fun recentSelcetAll(visibleYn : Boolean): List<WordEntity>

    @Query("SELECT * FROM word WHERE visibleYn = :visibleYn ORDER BY count DESC")
    fun countSelcetAll(visibleYn : Boolean): List<WordEntity>

    @Query("UPDATE word SET visibleYn = :visibleYn WHERE id = :id")
    fun deleteById(visibleYn : Boolean , id : Long)

    @Transaction
    fun upsertWord(model : WordEntity) {

        val word = selectWord(model.word)
        if (word == null){
            insert(model)
        }else {
            word.count++

            updateCount(word.word,word.count,model.timestamp)
        }
    }

    @Query("UPDATE word SET count = :count , timestamp = :timestamp  WHERE word = :word")
    fun updateCount(word : String , count : Int , timestamp : String)

    @Query("SELECT * FROM word WHERE word = :word LIMIT 1")
    fun selectWord(word : String) : WordEntity?
}