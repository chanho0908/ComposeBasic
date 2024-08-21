package com.example.composebasic.chap51

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composebasic.chap51.ui.theme.ComposeBasicTheme
import kotlinx.coroutines.flow.SharedFlow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBasicTheme {
                ScreenSetUp()
            }
        }
    }
}

@Composable
fun ScreenSetUp(viewModel: MainViewModel = viewModel()){
    MainScreen(viewModel.sharedFlow)
}

@Composable
fun MainScreen(sharedFlow: SharedFlow<Int>){
    val msg = remember { mutableStateListOf<Int>() }

    LaunchedEffect(key1 = Unit) {
        LocalLifecycleOwner.current.repeatOnLifecycle(Lifecycle.State.STARTED){
            sharedFlow.collect{
                msg.add(it)
            }
        }
    }

    LazyColumn {
        items(msg.size){
            Text(
                "Collected value = $it",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview17() {
    ComposeBasicTheme {
        val viewModel = MainViewModel()
        MainScreen(viewModel.sharedFlow)
    }
}