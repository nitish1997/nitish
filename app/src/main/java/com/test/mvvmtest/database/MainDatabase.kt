package com.test.mvvmtest.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test.mvvmtest.database.dao.PostTableDao
import com.test.mvvmtest.database.entity.PostTable
@Database(entities = arrayOf(PostTable::class), version = 1, exportSchema = false)
public abstract class MainDatabase: RoomDatabase() {
        abstract fun postDao():PostTableDao
        companion object{
            @Volatile
            private var INSTANCE:MainDatabase?=null
            fun getDatabase(context: Context):MainDatabase{
                val tempInstance = INSTANCE
                if (tempInstance != null) {
                    return tempInstance
                }
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        MainDatabase::class.java,
                        "PostDatabase"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                    return instance
                }
            }
        }
}
