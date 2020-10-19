package com.atoming.notepadkotlin.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddLinkFactoryModel(val app: Application) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddLinkViewModel(app) as T
    }

}