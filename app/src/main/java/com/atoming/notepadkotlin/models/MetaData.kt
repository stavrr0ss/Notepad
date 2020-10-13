package com.atoming.notepadkotlin.models

import android.webkit.URLUtil
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException
import java.net.URI
import java.net.URISyntaxException


class MetaData

// code copied and adapted from an nonfunctional library
// "https://github.com/ponnamkarthik/RichLinkPreview/blob/master/richlinkpreview/src/main/java/io/github/ponnamkarthik/richlinkpreview/RichPreview.java"
fun getResponse(url: String): MetaResponse {
    var doc: Document? = null
    val metaData: MetaResponse = MetaResponse()
    try {
        doc = Jsoup.connect(url)
            .get()
        val elements: Elements = doc.getElementsByTag("meta")

        // getTitle doc.select("meta[property=og:title]")
        var title: String = doc.select("meta[property=og:title]").attr("content")
        if (title == null || title.isEmpty()) {
            title = doc.title()
        }
        metaData.title = title

        //getDescription
        var description: String = doc.select("meta[name=description]").attr("content")
        if (description.isEmpty() || description == null) {
            description = doc.select("meta[name=Description]").attr("content")
        }
        if (description.isEmpty() || description == null) {
            description = doc.select("meta[property=og:description]").attr("content")
        }
        if (description.isEmpty() || description == null) {
            description = doc.select("body p").first().text()
        }
        metaData.description = description


        //getImages
        val imageElements = doc.select("meta[property=og:image]")
        if (imageElements.size > 0) {
            val image: String = imageElements.attr("content")
            if (!image.isEmpty()) {
                metaData.image = resolveURL(url, image)
            }
        }
        if (metaData.image.isEmpty()) {
            var src: String = doc.select("link[rel=image_src]").attr("href")
            if (!src.isEmpty()) {
                metaData.image = resolveURL(url, src)
            } else {
                src = doc.select("link[rel=apple-touch-icon]").attr("href")
                if (!src.isEmpty()) {
                    metaData.image = resolveURL(url, src)
                } else {
                    src = doc.select("link[rel=icon]").attr("href")
                    if (!src.isEmpty()) {
                        metaData.image = resolveURL(url, src)
                    }
                }
            }
        }

        for (element in elements) {
            if (element.hasAttr("property")) {
                val str_property: String = element.attr("property").toString().trim()
                if (str_property == "og:url") {
                    metaData.urlLink = element.attr("content").toString()
                }
                if (str_property == "og:site_name") {
                    metaData.siteName = element.attr("content").toString()
                }
            }
        }
        if (metaData.urlLink.equals("") || metaData.urlLink.isEmpty()) {
            var uri: URI? = null
            try {
                uri = URI(url)
            } catch (e: URISyntaxException) {
                e.printStackTrace()
            }
            if (url == null) {
                metaData.urlLink = url
            } else {
                metaData.urlLink = uri!!.host
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return metaData
}

private fun resolveURL(url: String, part: String): String {
    return if (URLUtil.isValidUrl(part)) {
        part
    } else {
        var base_uri: URI? = null
        try {
            base_uri = URI(url)
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
        base_uri = base_uri!!.resolve(part)
        base_uri.toString()
    }
}

data class MetaResponse(
    var title: String = "",
    var image: String = "",
    var description: String = "",
    var urlLink: String = "",
    var siteName: String = ""
)