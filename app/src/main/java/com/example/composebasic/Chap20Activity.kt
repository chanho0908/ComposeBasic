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

/*
*  상태(state)
*  선언형 UI에서 상태란, 시간에 따라 변경 될 수 있는 데이터
*  일반 변수 vs 상태
*  유사성 : 실행 중 변경될 수 있는 값을 저장한다.
*  차이점
*    - 상태의 값은 항상 기억 되어야 한다.
*    - 상태를 포함 하는 Composable 함수는 호출할 때 마다 마지막 상태를 기억해야 한다.
*    - 반면 표준 변수는 선언된 함수를 호출할 때 마다 초기화 된다.
*    - 상태의 변경은 컴포저블 함수 계층 트리 전체에 영향을 미친다.
*
*  재구성(recomposition)
*  부모 컴포저블의 상태 변화가 모든 자식 컴포저블에 반영되며 상태가 전달된다.
*  재구성은 컴포저블 함수의 계층 안에서 상태변회가 될 때 일어난다.
*
* */
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
    //rememberSaveable : Configuration Change (화면 가로 전환등)
    // 이 일어났을 때 도 상태를 유지
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