package com.example.composebasic

import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebasic.ui.theme.ComposeBasicTheme

class Chap20Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBasicTheme {
                Surface( color = MaterialTheme.colorScheme.background){
                    DemoScreen()
                }
            }
        }
    }
}

@Composable
fun DemoScreen(){
    var textState by rememberSaveable { mutableStateOf("") }
    var switchState by rememberSaveable { mutableStateOf(false) }

    val onTextChange = { text: String->
        textState = text
        if(text.isNotEmpty()){
            switchState = true
        }
    }

    val onSwitchChange = { value : Boolean ->
        switchState = value
        if(!value){
            textState = ""
        }
    }

    MyTextField(textState, onTextChange)
    MySwitch(switchState, onSwitchChange)
}

@Composable
fun MyTextField(text: String, onTextChange: (String) -> Unit){
    TextField(value = text, onValueChange = onTextChange)
}

@Composable
fun MySwitch(switchState: Boolean, onSwitchChange: (Boolean) -> Unit){
    Switch(checked = switchState, onCheckedChange = onSwitchChange)
}

@Composable
fun FunctionA(){
    var switchState by remember { mutableStateOf(true) }

    val onSwitchChange = { value : Boolean ->
        switchState = value
    }

    FunctionB(switchState = switchState, onSwitchChange)
}

@Composable
fun FunctionB(switchState: Boolean, onSwitchChange: (Boolean) -> Unit){
    Switch(checked = switchState, onCheckedChange = onSwitchChange)
}

@Preview
@Composable
fun DefaultPreView(){
    ComposeBasicTheme {
        Column {
            DemoScreen()
            //FunctionA()
        }

    }
}