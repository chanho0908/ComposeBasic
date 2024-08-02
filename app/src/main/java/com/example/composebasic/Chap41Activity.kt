package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebasic.ui.theme.ComposeBasicTheme

class Chap41Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // 최신 모습을 위해 edge-to-edge 디스플레이를 활성화합니다.
        setContent {
            ComposeBasicTheme {
                Greeting8()
            }
        }
    }
}

@Composable
fun Greeting8(viewModel: DemoViewModel = DemoViewModel()) {
    MyScreen(
        isFahrenheit = viewModel.isFahrenheit, // 상태: 화씨를 사용할지 여부.
        result = viewModel.result, // 상태: 변환된 온도 결과.
        convertTemp = { viewModel.converTemp(it) }, // 이벤트: 온도 변환.
        switchChange = { viewModel.swtichChange() } // 이벤트: 온도 단위 전환.
    )
}

// 화면의 레이아웃을 정의하는 컴포저블 함수입니다.
@Composable
fun MyScreen(
    isFahrenheit: Boolean,
    result: String,
    convertTemp: (String) -> Unit,
    switchChange: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()) {
        var textState by remember { mutableStateOf("") }

        val onTextChange = { text: String ->
            textState = text
        }

        Text(
            text = "Temperature Converter", modifier = Modifier.padding(20.dp),
            style = MaterialTheme.typography.headlineSmall
        )

        InputRow(
            isFahrenheit = isFahrenheit,
            textState = "",
            switchChange = switchChange,
            onTextChange = { convertTemp(it) }
        )

        Text(result, modifier = Modifier.padding(20.dp), style = MaterialTheme.typography.headlineMedium)
        Button(onClick = { convertTemp(textState) }) {
            Text("Convert Temperature")
        }
    }
}

// 스위치와 텍스트 필드가 있는 입력 행을 표시하는 컴포저블 함수입니다.
@Composable
fun InputRow(
    isFahrenheit: Boolean,
    textState: String,
    switchChange: () -> Unit,
    onTextChange: (String) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) { // 아이템을 세로 가운데에 정렬합니다.
        Switch(checked = isFahrenheit, onCheckedChange = { switchChange() }) // 단위 전환 스위치.

        OutlinedTextField(
            value = textState,
            onValueChange = { onTextChange(it) }, // 이벤트: 텍스트 입력 변경.
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), // 숫자만 입력 가능.
            singleLine = true, // 한 줄 입력.
            label = { Text(text = "온도를 입력하세요") }, // 텍스트 필드의 레이블.
            modifier = Modifier.padding(10.dp), // 텍스트 필드 주위에 패딩을 추가합니다.
            textStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp), // 텍스트 스타일.
            trailingIcon = { // 텍스트 필드 끝에 있는 아이콘.
                Icon(
                    painter = painterResource(id = R.drawable.baseline_ac_unit_24),
                    contentDescription = "온도계",
                    modifier = Modifier.size(40.dp) // 아이콘 크기를 설정합니다.
                )
            }
        )

        Crossfade(targetState = isFahrenheit, animationSpec = tween(2000)) { visible ->
            // 온도 단위 간의 애니메이션 전환.
            when (visible) {
                true -> Text(text = "\u2109", style = MaterialTheme.typography.headlineMedium) // 화씨
                false -> Text(
                    text = "\u2103",
                    style = MaterialTheme.typography.headlineMedium
                ) // 섭씨
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreviewA(model: DemoViewModel = DemoViewModel()) {
    ComposeBasicTheme {
        MyScreen(
            isFahrenheit = model.isFahrenheit,
            result = model.result,
            convertTemp = { model.converTemp(it) },
            switchChange = { model.swtichChange() }
        )
    }
}