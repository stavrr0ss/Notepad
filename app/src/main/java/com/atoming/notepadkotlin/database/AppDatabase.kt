package com.atoming.notepadkotlin.database

import android.content.Context
import androidx.room.*
import com.atoming.notepadkotlin.models.DbObject
import com.atoming.notepadkotlin.models.MetaResponse
import com.atoming.notepadkotlin.models.Note
import com.atoming.notepadkotlin.models.Task

@Database(
    entities = [DbObject::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
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
                        "Notes_database"
                    )
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}