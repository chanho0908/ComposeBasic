package com.example.composebasic.chap50

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    val myFlow: Flow<Int> = flow {
        for (i in 1..10){
            emit(i)
            //delay(2000)
        }
    }

    val newFlow = myFlow.map {
        "Current value = $it"
    }

    private val _stateFlow = MutableStateFlow(0)
    val stateFlow = _stateFlow.asStateFlow()

    fun increment(){
        _stateFlow.value += 1
    }
    /**
     * replay : 버퍼의 크기
     onBufferOverflow
         - DROP_OLDEST : 가장 오래된 값을 버림
         - DROP_LATEST : 가장 최근 값을 버림
         - SUSPEND : 버퍼가 가득찼을 때 suspend
     **/
    private val _sharedFlow = MutableSharedFlow<Int>(replay = 10, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun startSharedFlow(){
        viewModelScope.launch {
            for (i in 1..10) {
                _sharedFlow.emit(i)
                delay(1000)
            }
        }
    }

//        결과를 가공하거나 여러 값을 방출
//        transform {
//            emit("Value = $it")
//            delay(1000)
//            val doubled = it * 2
//            emit("Double value = $doubled")
//        }
}
