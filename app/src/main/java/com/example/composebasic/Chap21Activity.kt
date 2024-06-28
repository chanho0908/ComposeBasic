package com.example.composebasic

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebasic.ui.theme.ComposeBasicTheme
import org.w3c.dom.Text

// CompositionLocal
// 중간 자식 노드에 상태를 전달하지 않고도 루트 노드의 데이터를 하위 노드에 전달할 수 있다.
class Chap21Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBasicTheme {
                Surface {

                }
            }
        }
    }
}

val LocalColor = staticCompositionLocalOf { Color(0xFFffdbcf) }

@Composable
fun Composable1(){
    val color = if (isSystemInDarkTheme()){
        Color(0xFFa08d87)
    }else{
        Color(0xFFffdbcf)
    }

    Column {
        Composable2()

        CompositionLocalProvider(LocalColor provides color) {
            Composable3()
        }
    }
}

@Composable
fun Composable2(){
    Composable4()
}

@Composable
fun Composable3(){
    Text("Composable 3", modifier = Modifier.background(LocalColor.current))
    Composable5()
}

@Composable
fun Composable4(){
    Composable6()
}

@Composable
fun Composable5(){
    Text("Composable 5", modifier = Modifier.background(LocalColor.current))

    CompositionLocalProvider(LocalColor provides Color.Green) {
        Composable7()
    }

    CompositionLocalProvider(LocalColor provides Color.Green) {
        Composable8()
    }
}

@Composable
fun Composable6(){
    Text(text = "Composable 6", modifier = Modifier.background(LocalColor.current))
}

@Composable
fun Composable7(){
    Text("Composable 7", modifier = Modifier.background(LocalColor.current))
}

@Composable
fun Composable8(){
    Text("Composable 8", modifier = Modifier.background(LocalColor.current))
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DarkPreview(){
    ComposeBasicTheme {
        Composable1()
    }
}