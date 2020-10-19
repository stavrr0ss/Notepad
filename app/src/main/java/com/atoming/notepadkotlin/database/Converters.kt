package com.atoming.notepadkotlin.database

import androidx.room.TypeConverter
import com.atoming.notepadkotlin.models.MetaResponse
import com.atoming.notepadkotlin.models.Task
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class Converters {

    @TypeConverter
    fun tasksFromString(stringList: String?): List<Task?>? {
        val type: Type = object : TypeToken<List<Task?>?>() {}.type
        return Gson().fromJson(stringList, type)
    }

    @TypeConverter
    fun tasksFromArray(list: List<Task?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun linksFromString(stringList: String?): List<MetaResponse?>? {
        val type: Type = object : TypeToken<List<MetaResponse?>?>() {}.type
        return Gson().fromJson(stringList, type)
    }

    @TypeConverter
    fun linksFromArray(list: List<MetaResponse?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}