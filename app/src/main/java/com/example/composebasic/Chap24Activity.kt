package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebasic.ui.theme.ComposeBasicTheme
/**
 *
 *  Modifier
 *  컴포저블에 적용될 속성들을 저장한다.
 *  테두리, 패딩, 배경, 크기, 이벤트 핸들러, 제스처 등 다양한 프로퍼티를 설정할 수 있다.
 *
 *  컴포저블이 모디파이어를 받는 경우 항상 모디파이어가 파라미터 리스트의 첫 번째 파라미터
 * **/
class Chap24Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DemoScreen2()
                }
            }
        }
    }
}

@Composable
fun DemoScreen2(){
    val modifier = Modifier
        .border(width = 2.dp, color = Color.Black)
        .padding(all = 10.dp)
    val modifier2 = Modifier.height(100.dp)

    Column(
        Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Hello Compose",
            modifier = modifier.then(modifier2),
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomImage(image = R.drawable.vacation,
            Modifier.padding(16.dp)
                .width(270.dp)
                .clip(RoundedCornerShape(30.dp))
        )
    }

}

@Composable
fun CustomImage(image: Int, modifier: Modifier = Modifier){
    Image(painter = painterResource(id = image), contentDescription = null, modifier)
}

@Preview
@Composable
fun Chap24PreView(){
    ComposeBasicTheme {
        DemoScreen2()
    }
}