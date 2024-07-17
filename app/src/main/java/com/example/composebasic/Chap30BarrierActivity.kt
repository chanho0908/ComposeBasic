package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.composebasic.ui.theme.ComposeBasicTheme

class Chap30BarrierActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBasicTheme {
                BarrierScreen()
            }
        }
    }
}

@Composable
fun BarrierScreen() {
    ConstraintLayout(Modifier.size(width = 350.dp, height = 220.dp)) {
        val (button1, button2, button3) = createRefs()

        val barrier = createEndBarrier(button1, button2)

        BarrierButton(text = "Button1", Modifier.width(100.dp).constrainAs(button1) {
            top.linkTo(parent.top, margin = 30.dp)
            start.linkTo(parent.start, margin = 8.dp)
        })

        BarrierButton(text = "Button2", Modifier.width(150.dp).constrainAs(button2) {
            top.linkTo(button1.bottom, margin = 20.dp)
            start.linkTo(parent.start, margin = 8.dp)
        })

        BarrierButton(text = "Button3", Modifier.constrainAs(button3) {
            linkTo(parent.top, parent.bottom, topMargin = 8.dp, bottomMargin = 8.dp)
            linkTo(button1.end, parent.end, startMargin = 30.dp, endMargin = 8.dp)
            start.linkTo(barrier, margin = 30.dp)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        })
    }
}

@Composable
fun BarrierButton(text: String, modifier: Modifier = Modifier){
    Button(
        onClick = {},
        modifier = modifier
    ){
        Text(text)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    ComposeBasicTheme {
        BarrierScreen()
    }
}