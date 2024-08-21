package com.example.composebasic.chap50

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composebasic.chap50.ui.theme.ComposeBasicTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.flow.zip
import kotlin.system.measureTimeMillis

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBasicTheme {
                ScreenSetup()
            }
        }
    }
}

@Composable
fun ScreenSetup(viewModel: MainViewModel = viewModel()){
    MainScreen(viewModel)
}

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun MainScreen(viewModel: MainViewModel){

    val count2 by viewModel.stateFlow.collectAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment =  Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "$count2", style = TextStyle(fontSize = 40.sp))
        Button(onClick = { viewModel.increment() }){
            Text("Click Me")
        }
    }
    var count by remember { mutableStateOf("") }

//    LaunchedEffect(Unit) {
////        viewModel.myFlow.flatMapConcat { viewModel.doubleIt(it) }
////            .collect { count = it }
//        // 두 개의 flow를 단일 flow로 조합
//        val flow1 = (1..5).asFlow().onEach { delay(1000) }
//        val flow2 = flowOf("one", "two", "three", "four", "five").onEach { delay(1500) }
//        flow1.zip(flow2){ value, string -> "$value -> $string" }.collect{ count = it }
//
//        // 두 개의 flow를 단일 flow로 조합
//        // 다른 flow에서 값이 방출되지 않을 경우 해당 플로우의 최신값 사용
//        flow1.combine(flow2){ value, string -> "$value -> $string" }.collect{ count = it }
//    }

//        try {
//            flow.collect {
//                count = it
//            }
//        }finally {
//            // 수집이 끝난 후 구현하고 싶은 동작
//            count = "Flow Stream Ended"
//        }
//        val elapsedTime = measureTimeMillis {
//            flow
//                .buffer() // 모든 값을 수집하면서 사용자가 수집된 값을 소비하지 않아도 순차적으로 들어온 값을을 저장함
//                .collect{
//                count = it
//                delay(1000)
//            }
//        }
//        // 누산기
//        flow.reduce{ accumulator, value ->
//            count = "$accumulator $value"
//            accumulator + value
//        }
        // 초기값을 가지는 누산기
//        flow.fold(10){ accumulator, value ->
//                count = accumulator
//                accumulator + value
//            }

    val count3 by viewModel.sharedFlow.collectAsState(0)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment =  Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "$count3", style = TextStyle(fontSize = 40.sp))
        Button(onClick = { viewModel.startSharedFlow() }){
            Text("Click Me")
        }
    }

//    Column(
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment =  Alignment.CenterHorizontally,
//        modifier = Modifier.fillMaxSize()
//
//    ) {
//        Text(text = "$count", style = TextStyle(fontSize = 40.sp))
//    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview16() {
    ComposeBasicTheme {
        ScreenSetup(viewModel())
    }
}