package com.atoming.notepadkotlin.ui

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.atoming.notepadkotlin.R
import com.atoming.notepadkotlin.models.MetaResponse
import com.atoming.notepadkotlin.models.Note
import com.atoming.notepadkotlin.models.getResponse
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val LOG_TAG: String = MainActivity::class.java.simpleName
    private val url = "https://developer.android.com/kotlin/common-patterns"
    private var metaResponse: MetaResponse = MetaResponse()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}