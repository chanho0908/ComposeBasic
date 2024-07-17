package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composebasic.ui.theme.ComposeBasicTheme

class Chap30_2Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBasicTheme {
                ConstScreen()
            }
        }
    }
}

@Composable
fun ConstScreen2() {
    ConstraintLayout(Modifier.size(400.dp, 250.dp)) {
        val (button1, button2, button3) = createRefs() // 참조를 생성
        val guide = createGuidelineFromStart(fraction = .60f)

        MyButton2(text = "Button1", Modifier.constrainAs(button1) {
            top.linkTo(parent.top, margin = 30.dp)
            end.linkTo(guide, margin = 30.dp)
        })

        MyButton2(text = "Button2", Modifier.constrainAs(button2) {
            top.linkTo(button1.bottom, margin = 20.dp)
            end.linkTo(guide, margin = 40.dp)
        })

        MyButton2(text = "Button3", Modifier.constrainAs(button3) {
            top.linkTo(button2.bottom, margin = 40.dp)
            end.linkTo(guide, margin = 20.dp)
        })

//        createHorizontalChain(button1, button2, button3,
//            chainStyle = ChainStyle.SpreadInside) // 체인 생성
//        MyButton2(text = "Button1", Modifier.constrainAs(button1) {
//            centerVerticallyTo(parent)
//        })
//
//        MyButton2(text = "Button2", Modifier.constrainAs(button2) {
//            centerVerticallyTo(parent)
//        })
//        MyButton2(text = "Button3", Modifier.constrainAs(button3) {
//            centerVerticallyTo(parent)
//        })

    }
}

@Composable
fun MyButton2(text: String, modifier: Modifier = Modifier){
    Button(
        onClick = {},
        modifier = modifier
    ){
        Text(text)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    ComposeBasicTheme {
        ConstScreen2()
    }
}