package com.atoming.notepadkotlin.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tasks")
data class Task(
    var text: String,
    var postedAt: Long,
    var color: Int,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)