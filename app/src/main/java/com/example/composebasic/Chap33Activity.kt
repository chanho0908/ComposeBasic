package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasic.ui.theme.ComposeBasicTheme
import kotlinx.coroutines.launch

/**
 * Row, Column 컴포넌트를 통해 리스트를 만들면 초기화 시점에
 * 리스트에 포함된 모든 아이템을 만들어 메모리 부족과 성능 제한일 발생 시킨다.
 *
 * LazyColumn, LazyRow LazyVerticalGride를 사용하면
 * 사용자에게 실제로 보이는 아이템만 만들고 스크롤시 표시 영역에서 벗어나는 아이템들은
 * 파괴하고 리소스를 확보하고 아이템들은 표시되는 시점에 만들어진다
 *
 * 이를 활용하면 "무한 스크롤" 도 성능 저하없이 만들 수 있다.
 *
 * ScollState
 * Row, Column은 ScrollState을 통해 스크롤을 활성화할 수 있다.

 * val scrollState = rememberScrollState()
 *
 * -> Column(Modifier.verticalScroll(scrollState)) {}
 * -> Row(Modifier.horizontalScroll(scrollState)) {}
 *
 * Row, Column 기반 특정 위치로 스크롤하기
 * animateScrollTo(value: Int) : 애니메이션을 이용해 지정한 픽셀 위치까지 부드럽게 스크롤
 * scrollTo(value: Int) : 지정 위치까지 스크롤
 * rememberScrollState().maxValue : 가장 하단 위치
 *
 * LazyRow, LazyColumn 기반 특정 위치로 스크롤하기
 * val listState = rememberLazyListState 를 사용해 LazyListState 인스턴스를 만든 후,
 * LazyColum(state = listState)
 * LazyRow(state = listState)
 *
 * animateScrollToItem(index: Int) : 애니메이션을 이용해 지정한 픽셀 위치까지 부드럽게 스크롤
 * scrollToItem(index: Int) : 지정 위치까지 스크롤
 * */

class Chap33Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBasicTheme {
                Greeting2()
            }
        }
    }
}

@Composable
fun Greeting2() {
    //ColumnList()
    RowList()
}
@Composable
fun RowList() {

    val scrollState = rememberScrollState()

    Row(Modifier.horizontalScroll(scrollState)) {
        repeat(50) {
            Text(" $it ",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(5.dp))
        }
    }
}
@Composable
fun ColumnList() {

    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    Column {

        Row {
            Button(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollTo(0)
                }
            },
                modifier = Modifier.weight(0.5f)
                    .padding(2.dp)) {
                Text("Top")
            }

            Button(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollTo(scrollState.maxValue)
                }
            },
                modifier = Modifier.weight(0.5f)
                    .padding(2.dp)) {
                Text("End")
            }
        }

        Column(Modifier.verticalScroll(scrollState)) {
            repeat(500) {
                Text(
                    "List Item $it",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(5.dp)
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    ComposeBasicTheme {
        Greeting2()
    }
}