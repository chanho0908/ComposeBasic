package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasic.ui.theme.ComposeBasicTheme
import kotlin.math.roundToInt

class Chap27Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen4()
                }
            }
        }
    }
}

@Composable
fun MainScreen4() {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.size(120.dp, 80.dp)) {
        Column {
            ColorBox(
                modifier = Modifier
                    .exampleLayout(0f)
                    .background(Color.Blue)
            )
            ColorBox(
                modifier = Modifier
                    .exampleLayout(0.25f)
                    .background(Color.Green)
            )
            ColorBox(
                modifier = Modifier
                    .exampleLayout(0.5f)
                    .background(Color.Yellow)
            )
            ColorBox(
                modifier = Modifier
                    .exampleLayout(0.25f)
                    .background(Color.Red)
            )
            ColorBox(
                modifier = Modifier
                    .exampleLayout(0.0f)
                    .background(Color.Magenta)
            )
        }
    }
}

fun Modifier.exampleLayout(
    fraction: Float
) = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints = constraints)

    val firstBaseline = placeable[FirstBaseline]
    val laseBaseLine = placeable[LastBaseline]

    val x = -(placeable.width * fraction).roundToInt()

    layout(placeable.width, placeable.height) {
        placeable.placeRelative(x, 0)
    }
}

@Composable
fun ColorBox(modifier: Modifier) {
    Box(
        modifier = Modifier
            .padding(1.dp)
            .size(width = 50.dp, height = 10.dp)
            .then(modifier)
    )
}

@Preview(apiLevel = 33)
@Composable
fun Preview() {
    MainScreen4()
}