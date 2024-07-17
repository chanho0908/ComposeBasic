package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.composebasic.ui.theme.ComposeBasicTheme

class ConstraintSetActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBasicTheme {
                ConstraintSetScreen()
            }
        }
    }
}

@Composable
fun ConstraintSetScreen() {
    ConstraintLayout(Modifier.size(width = 350.dp, height = 220.dp)) {
        val constraints = myConstraintSet(8.dp)

        SetButton(text = "Button1", Modifier.size(200.dp).layoutId("button1"))


    }
}

private fun myConstraintSet(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val button1 = createRefFor("button1")

        constrain(button1) {
            linkTo(parent.top, parent.bottom, topMargin = margin,
                bottomMargin = margin)
            linkTo(parent.start, parent.end, startMargin = margin,
                endMargin = margin)
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
        }
    }
}

@Composable
fun SetButton(text: String, modifier: Modifier = Modifier){
    Button(
        onClick = {},
        modifier = modifier
    ){
        Text(text)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    ComposeBasicTheme {
        ConstraintSetScreen()
    }
}