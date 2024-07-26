package com.example.composebasic

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasic.ui.theme.ComposeBasicTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import kotlinx.coroutines.launch

private var itemArray: Array<String>? = null

class Chap36Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        itemArray = resources.getStringArray(R.array.car_array)

        setContent {
            ComposeBasicTheme {
                Greeting5(itemArray as Array<out String>)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Greeting5(itemList: Array<out String>) {
    val context = LocalContext.current
    // 매핑된 리스트로 그룹핑
    val groupedItems = itemList.groupBy {
        it.substringBefore(' ') // 첫 번째 단어만 추출
    }

    val onListItemClick = { text: String ->
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    // 버튼 표시 여부
    val displayButton = listState.firstVisibleItemIndex > 5

    Box {
        // 스크롤 가능한 리스트
        LazyColumn(
            state = listState, // 리스트 상태
            contentPadding = PaddingValues(bottom = 40.dp) // 패딩 추가

        ) {
            groupedItems.forEach { (manufacturer, models) ->
                stickyHeader {
                    Text(
                        text = manufacturer,
                        color = Color.White,
                        modifier = Modifier
                            .background(Color.Gray)
                            .padding(5.dp)
                            .fillMaxWidth()
                    )
                }
                items(models) { model ->
                    MyListItem(item = model, onItemClick = onListItemClick)
                }
            }
        }

        AnimatedVisibility(
            visible = displayButton,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            OutlinedButton(
                onClick = {
                    coroutineScope.launch {
                        listState.scrollToItem(0)
                    }
                },
                border = BorderStroke(1.dp, Color.Gray),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.DarkGray
                ),
                modifier = Modifier.padding(5.dp),
            ) {
                Text(text = "Top")
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview9() {
    val itemArray: Array<String> = arrayOf(
        "Cadillac Eldorado",
        "Ford Fairlane", "Plymouth Fury"
    )

    ComposeBasicTheme {
        Greeting5(itemArray)
    }
}