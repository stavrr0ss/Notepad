package com.atoming.notepadkotlin.models

import androidx.annotation.Nullable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.atoming.notepadkotlin.database.Converters

@Entity(tableName = "dbObject")
data class DbObject(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @Nullable
    @TypeConverters(Converters::class)
    var tasks: List<Task>?,
    @Nullable
    @Embedded
    var note: Note?,
    @Nullable
    @TypeConverters(Converters::class)
    var links: List<MetaResponse>?

)