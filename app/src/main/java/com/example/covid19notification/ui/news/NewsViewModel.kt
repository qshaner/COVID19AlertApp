package com.example.covid19notification.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Get updates on COVID in your area!"
    }
    val text: LiveData<String> = _text
}