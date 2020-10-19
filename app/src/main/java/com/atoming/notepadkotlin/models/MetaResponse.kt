package com.atoming.notepadkotlin.models

import android.webkit.URLUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.atoming.notepadkotlin.database.Converters
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException
import java.net.URI
import java.net.URISyntaxException


data class MetaResponse(
    var title: String = "",
    var image: String = "",
    var description: String = "",
    var urlLink: String = "",
    var siteName: String = "",
    @PrimaryKey(autoGenerate = true)
    var metaId: Int = 0
)