package com.example.composebasic

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    var customCount by mutableIntStateOf(0)
    var customerName : MutableLiveData<String> = MutableLiveData("")

    fun increaseCount() {
        customCount++
    }

    fun setName(name: String){
        customerName.value = name
    }
}