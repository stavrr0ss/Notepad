package com.atoming.notepadkotlin.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Note(
    var description: String,
    var postedAt: Long,
    var title: String,
    var color: Int,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)