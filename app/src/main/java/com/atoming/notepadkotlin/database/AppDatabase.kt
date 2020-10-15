package com.atoming.notepadkotlin.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.atoming.notepadkotlin.models.DbObject
import com.atoming.notepadkotlin.models.MetaResponse
import com.atoming.notepadkotlin.models.Note
import com.atoming.notepadkotlin.models.Task

@Database(
    entities = [DbObject::class, Note::class, MetaResponse::class, Task::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract val mDao: MyDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "Notes database"
                    )
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}