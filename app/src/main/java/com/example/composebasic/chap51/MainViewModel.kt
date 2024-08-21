package com.example.composebasic.chap51

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _sharedFlow = MutableSharedFlow<Int>()
    val sharedFlow = _sharedFlow.asSharedFlow()
    init {
        sharedFlowInit()
    }

    fun sharedFlowInit(){
        viewModelScope.launch {
            for (i in 1..1000){
                _sharedFlow.emit(i)
            }
        }
    }
}