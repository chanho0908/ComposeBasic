package com.example.composebasic

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebasic.ui.theme.ComposeBasicTheme
import org.w3c.dom.Text

class Chap19MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBasicTheme {
                    CustomSwitch()
                }
        }
    }
}

@Composable
fun CustomList(items: List<String>){
    Column {
        for (item in items){
            Text(item)
            HorizontalDivider(color = Color.Blue)
        }
    }
}

@Preview
@Composable
fun DefaultPreview2(){
    ComposeBasicTheme {
        CustomList(listOf("One", "Two", " Three", "Four"))
    }
}

@Composable
fun CustomSwitch(){
    val checked = remember { mutableStateOf(true) }

    Column {
        Switch(checked = checked.value, onCheckedChange = { checked.value = it })
        if (checked.value) Text(text = "Switch is On")
        else Text(text = "Switch is Off")
    }
}

@Composable
fun CustomText(text: String, fontWeight: FontWeight, color: Color) {
    Text(text = text, fontWeight = fontWeight, color = color)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CustomText(text = "Hello Compose", fontWeight = FontWeight.Bold, color = Color.Magenta)
}


