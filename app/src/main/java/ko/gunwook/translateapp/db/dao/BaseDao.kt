package ko.gunwook.translateapp.db.dao

import androidx.room.*

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj : T)

    @Delete
    fun delete(obj : T)

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun update(obj : T)
}