package com.atoming.notepadkotlin.viewmodels

import android.app.Application
import android.webkit.URLUtil
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.atoming.notepadkotlin.models.DbObject
import com.atoming.notepadkotlin.models.MetaResponse
import com.atoming.notepadkotlin.repository.NotepadRepository
import kotlinx.coroutines.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException
import java.net.URI
import java.net.URISyntaxException

class AddLinkViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository: NotepadRepository
    private var viewmodelJob = Job()
    private val coroutinScope = CoroutineScope(Dispatchers.Main + viewmodelJob)
    private var _metaResponse = MutableLiveData<MetaResponse>()

    init {
        mRepository = NotepadRepository(application.applicationContext)
        //getLinkResponse(url)
    }

    fun getLinkResponse(url: String) {
        coroutinScope.launch {
            _metaResponse.value = mRepository.getResponse(url)
        }
    }

    fun insertNote(dbObject: DbObject) {
        coroutinScope.launch {
            mRepository.insertNote(dbObject)
        }
    }


    private val metaResponse: LiveData<MetaResponse>
        get() = _metaResponse

    fun getResponse(): LiveData<MetaResponse> {
        return metaResponse
    }

    override fun onCleared() {
        super.onCleared()
        viewmodelJob.cancel()
    }
}