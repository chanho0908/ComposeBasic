package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebasic.ui.theme.ComposeBasicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBasicTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    DemoScreen()
                }
            }
        }
    }

    @Composable
    fun DemoScreen(){
        // 리컴포지션 : 상태가 변경되면 재랜더링을 한다
        // 이 때, 컴포넌트 내부의 변수도 초기화 되는데
        // remember를 사용하면 최초에만 초기화를 진행하고
        // 이전 상태값을 기억하기 위해서는 remember를 사용한다.
        var sliderPosition by remember { mutableStateOf(20f) }

        val handlePostitionChange = { pos: Float ->
            sliderPosition = pos
        }

        // 레이아웃을 정렬
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, // 가로 정렬
            verticalArrangement = Arrangement.Center, // 세로 정렬
            modifier = Modifier.fillMaxSize()
        ) {
            DemoText(msg = "Welcome To First Compose", fontSize = sliderPosition)

            Spacer(modifier = Modifier.height(150.dp))

            DemoSlider(
                sliderPosition = sliderPosition,
                onPositionChange = handlePostitionChange
            )

            Text(
                style = MaterialTheme.typography.labelLarge,
                text = sliderPosition.toInt().toString() + "sp"
            )
        }
    }

    @Composable
    fun DemoSlider(sliderPosition: Float, onPositionChange: (Float) -> Unit){
        Slider(
            modifier = Modifier.padding(10.dp),
            valueRange = 20f..40f,
            value = sliderPosition,
            onValueChange = { onPositionChange(it) }
        )
    }

    @Composable
    fun DemoText(msg: String, fontSize: Float){
        Text(
            text = msg,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Bold
        )
    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun DemoTextPreView(){
        DemoScreen()
     //   DemoText(msg = "Welcome to My First Compose", fontSize = 12f)
    }
}

