package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasic.ui.theme.ComposeBasicTheme

/**
 * ViewModel은 컴포저블 계층의 맨 위에 위치한 컴포저블에서 수행할 것을 권장한다.
 *
 * */
class Chap40Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBasicTheme {
                TopLevel()
            }
        }
    }
}

@Composable
fun TopLevel(model: MyViewModel = MyViewModel()) {
    //var customerName: String by model.customerName.observeAsState("")
    Greeting7(model.customCount){ model.increaseCount() }

}

@Composable
fun Greeting7(count: Int, addCount: () -> Unit = {}) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()) {
        Text(text = "Total customers = $count", modifier = Modifier.padding(10.dp))
        Button(onClick = addCount) {
            Text(text = "Add a Customer")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview11() {
    ComposeBasicTheme {
        TopLevel()
    }
}