package com.atoming.notepadkotlin.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.atoming.notepadkotlin.database.Converters


data class Task(
    var text: String,
    @PrimaryKey(autoGenerate = true)
    var taskId: Int = 0
)