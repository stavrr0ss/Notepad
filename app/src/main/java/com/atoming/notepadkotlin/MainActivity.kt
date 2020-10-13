package com.atoming.notepadkotlin

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.atoming.notepadkotlin.models.MetaResponse
import com.atoming.notepadkotlin.models.getResponse
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class MainActivity : AppCompatActivity() {
    private val LOG_TAG: String = MainActivity::class.java.simpleName
    private val url = "https://developer.android.com/kotlin/common-patterns"
    private var metaResponse: MetaResponse = MetaResponse()

    lateinit var titleText: TextView
    lateinit var imageLink: ImageView
    lateinit var descriptionText: TextView
    lateinit var linkUrl: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = button
        titleText = link_title
        imageLink = image_link
        descriptionText = link_description
        linkUrl = link_urlText

        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch {
            setValues()
        }

        button.setOnClickListener {
            //setValues()
        }
    }

    suspend fun fetchDocument(url: String) = withContext(Dispatchers.IO) {
        metaResponse = getResponse(url)
    }

    suspend fun setValues() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            metaResponse.description.let {
                descriptionText.text = Html.fromHtml(it, Html.FROM_HTML_MODE_COMPACT)
                //descriptionText.text = it
                Log.d(LOG_TAG, "DESCRIPTION IS $it")
            }
            metaResponse.title.let {
                titleText.text = it
            }
            val imageUrl = metaResponse.image
            if (!imageUrl.isEmpty()) {
                imageLink.visibility = View.VISIBLE
                Glide.with(this).load(imageUrl).into(imageLink)
            }
            linkUrl.text = metaResponse.urlLink
        }
    }
}