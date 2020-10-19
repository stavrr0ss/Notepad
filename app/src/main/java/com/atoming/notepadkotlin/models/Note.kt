package com.atoming.notepadkotlin.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


data class Note(
    var description: String,
    var notePostedAt: Long,
    var noteTitle: String

)