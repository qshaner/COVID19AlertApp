package com.example.covid19notification.ui.resources

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResourceViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Here we will put resources: The symptom tracker, the ability to notify others"
    }
    val text: LiveData<String> = _text
}