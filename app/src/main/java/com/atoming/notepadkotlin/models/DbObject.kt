package com.atoming.notepadkotlin.models

import androidx.annotation.Nullable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dbObject")
data class DbObject(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @Embedded
    @Nullable
    var tasks: List<Task>,
    @Embedded
    @Nullable
    var note: Note,
    @Embedded
    @Nullable
    var links: List<MetaResponse>
)