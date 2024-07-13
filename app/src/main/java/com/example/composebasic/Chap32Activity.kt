package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebasic.ui.theme.ComposeBasicTheme
import kotlinx.coroutines.launch

class Chap32Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBasicTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
/**
 *    val coroutineScope = rememberCoroutineScope()
 *     coroutineScope.launch() {  }
 *  이런 식의 구현은 호출하는 컴포저블의 라이프 사이클을 고려하지 않고 다른 스코프로부터 컴포저블의 상태를 변경할 때
 *  부작용을 발생시킨다.
 *  흔히 xml에서 View의 LifeCycle에 종속되는 코루틴이 아닌 CoroutineScope를 쓰는 것을 생각하면 될 것 같다.
 *
 *  LaunchedEffect, SideEffect : 위 문제를 해결하기 위한 부모 컴포저블의 라이프 사이클을 인식하는 컴포저블이다.
 *  LaunchedEffect은 호출시 코루틴이 즉시 실행되고 완료되는 즉시 인스턴스와 코루틴을 디스트로이 한다.
 * */
    val coroutineScope = rememberCoroutineScope()

    /**
     * 최소 하나 이상의 key 파라미터가 필요하며 이 key는 재구성을 통해 코루틴의 동작을 통제한다.
     * 즉, key 파라미터값이 변경되지 않으면 LaunchEffect는 해당 부모 컴포저블이 여러번 재구성 되어도
     * 동일한 코루틴을 유지할 수 있게 해준다.
     *
     * 만약, 이 key 값이 변경되면 LaunchEffect는 현재 코루틴을 취소하고 새로운 코루틴을 실행한다.
     *
     * SlideEffect 에서의 코루틴은 부모의 재구성이 완료된 뒤 실행된다.
     * 또한 key 파라미터를 받지 않으며 부모 컴프저블이 재구성 될 때 마다 수행된다.
     * */
    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch {

        }
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    ComposeBasicTheme {
        Greeting("Android")
    }
}