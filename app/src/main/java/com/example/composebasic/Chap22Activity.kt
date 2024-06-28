package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebasic.ui.theme.ComposeBasicTheme

/*
*  Slot API
*  특정 컴포저블이 호출되기 전까지 어떤 컴포저블이 화면에 나타나는 것을 지연할 때 사용
*
* */

class Chap22Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SlotDemo(
                        { Text("Top Text") },
                        { ButtonDemo() },
                        { Text("Bottom Text") },
                    )
                }
            }
        }
    }
}

@Composable
fun SlotDemo(
    topContent: @Composable () -> Unit,
    middleContent: @Composable () -> Unit,
    bottomContent: @Composable () -> Unit

){
    Column {
        topContent()
        middleContent()
        bottomContent()
    }
}

@Composable
fun ButtonDemo(){
    Button(onClick = {}) {
        Text(text = "Click ME")
    }
}

@Preview
@Composable
fun SlotDemoPreview(){
    ComposeBasicTheme {
        SlotDemo(
            { Text("Top Text") },
            { ButtonDemo() },
            { Text("Bottom Text") },
        )
    }
}