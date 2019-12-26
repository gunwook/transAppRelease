package ko.gunwook.translateapp.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * @param id word Pk
 * @param word 단어
 * @param timestamp 생성시간
 * @param count 찾는 횟수
 * @param visibleYn 보이는 여부 설정
 * */
@Entity(tableName = "word")
data class WordEntity(@PrimaryKey(autoGenerate = true) val id : Long = 0, var word : String = "", var timestamp : String = "" , var transword : String = "" , var count : Int = 0 , var visibleYn : Boolean = true)