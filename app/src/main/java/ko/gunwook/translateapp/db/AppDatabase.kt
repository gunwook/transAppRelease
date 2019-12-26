package ko.gunwook.translateapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ko.gunwook.translateapp.db.dao.WordDao
import ko.gunwook.translateapp.db.entity.WordEntity

@Database(entities = arrayOf(WordEntity::class),version = 1 , exportSchema =  false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun WordDao(): WordDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {

            INSTANCE ?: synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app_database.db").build()
            }
            return INSTANCE
        }
    }
}