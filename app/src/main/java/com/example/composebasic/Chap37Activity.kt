package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasic.ui.theme.ComposeBasicTheme

class Chap37Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBasicTheme {
                Greeting6()
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Greeting6() {
    // 버튼 상태
    var boxVisible by remember { mutableStateOf(false) }
    // 버튼 클릭 이벤트
    val onClick = { newState: Boolean ->
        boxVisible = newState
    }

    // 애니메이션 상태(자동 시작 하기)
    val state = remember { MutableTransitionState(true) }

    // 애니메이션 시작
    state.apply { targetState = true }

    Column(
        Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // 버튼을 번갈아가면서 보여줌
            Crossfade(targetState = boxVisible,
                animationSpec = tween(5000)
            ) { visible ->
                when(visible){
                    true -> CustomButton(text = "Show", targetState = true, onClick = onClick)
                    else -> CustomButton(text = "Hide", targetState = false, onClick = onClick)
                }
            }
        }

        Spacer(modifier = Modifier.padding(20.dp))

        if (boxVisible) {
            AnimatedVisibility(
                visibleState = state,
                // 애니메이션 효과
                enter = fadeIn(
//                    animationSpec = tween(
//                        // FadeIn 애니메이션이 시작하는 지연 시간
//                        durationMillis = 5000,
//                        // FadeIn 애니메이션의 easing 곡선
//                        easing = LinearOutSlowInEasing
//                    ),
                    animationSpec = repeatable( // 반복 애니메이션
                        10, // 반복 횟수
                        animation = tween(durationMillis = 2000), // 반복 간격
                        repeatMode = RepeatMode.Reverse // 반복 방식
                    )
                ),
                exit = fadeOut()
            ) {
                Box(
                    modifier = Modifier
                        .animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = tween(durationMillis = 5500)
                            ),
                            exit = slideOutVertically(
                                animationSpec = tween(durationMillis = 5500)
                            )
                        )
                        .size(height = 200.dp, width = 200.dp)
                        .background(Color.Blue)
                )
            }
        }
    }
}

@Composable
fun CustomButton(
    text: String, targetState: Boolean,
    onClick: (Boolean) -> Unit, bgColor: Color = Color.Blue
) {

    Button(
        onClick = { onClick(targetState) },
        colors = ButtonDefaults.buttonColors(
            containerColor = bgColor,
            contentColor = Color.White
        )
    ) {
        Text(text)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview10() {
    ComposeBasicTheme {
        Greeting6()
    }
}