package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasic.ui.theme.ComposeBasicTheme
import kotlinx.coroutines.launch

class Chap34Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBasicTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting3(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {
    ColumList()
}

@Composable
fun ColumList(){

    // 스크롤을 활성화하기 위한 상태 인스턴스
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    Column(Modifier.verticalScroll(scrollState)) {
        Row {
            Button(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollTo(0)
                }
            },
                modifier = Modifier
                    .weight(0.5f)
                    .padding(2.dp)) {
                Text(text = "Top")
            }

            Button(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollTo(scrollState.maxValue)
                }
            },
                modifier = Modifier
                    .weight(0.5f)
                    .padding(2.dp)) {
                Text(text = "End")
            }
        }
        repeat(300){
            Text(text = "List Item $it",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}

@Composable
fun RowList2(){
    val scrollState = rememberScrollState()

    Row(Modifier.horizontalScroll(scrollState)) {
        repeat(10){ Text(text = "$it",) }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview7() {
    ComposeBasicTheme {
        Greeting3("Android")
    }
}