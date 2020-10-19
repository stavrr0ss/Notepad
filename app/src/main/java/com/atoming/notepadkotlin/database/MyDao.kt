package com.atoming.notepadkotlin.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.atoming.notepadkotlin.models.DbObject

@Dao
interface MyDao {

    @Insert
    suspend fun insertObject(savedObject: DbObject)

    @Query("SELECT * FROM dbObject")
    fun getAllNotesByDate(): LiveData<List<DbObject>>

    @Delete
    fun deleteNote(dbObject: DbObject)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(dbObject: DbObject)

}