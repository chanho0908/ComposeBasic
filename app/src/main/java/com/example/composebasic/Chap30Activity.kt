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
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composebasic.ui.theme.ComposeBasicTheme
/**
 * ConstraintLayout
 * 다양한 크기의 화면, 기기 회전으로 읻해 발생하는 변경에 자동으로 반응해야 하는 사용자 인터페이스 레이아웃을 개발할 수 있다.
 * 자식 컴포넌트들의 위치 및 크기 동작을 관리하며 각 자식에 설정된 제약 커넥션 기반으로 이를 수행한다.
 *
 * ConstraintLayout 구성 요소
 *  1. 제약
 *    일련의 규칙들로 컴포저블의 정렬과 위치를 조정할 때 다른 컴포저블, ConstraintLayout 부모를 포함한 관계,
 *    가이드 라인, 베리어 등을 기준으로 상대적으로 규칙을 지정한다.
 *
 *  2. 마진
 *    고정된 거리를 지정하는 제약의 한 형태.
 *
 *  3. 반대 제약
 *    동일한 축을 따라 한 컴포저블이 가진 2개의 제약
 *    즉, 한 컴포넌트가 왼쪽과 오른쪽 가장자리에 모두 제약을 가질 때 수평 반대 제약을 가졌다고 한다.
 *    반대 제약이 구현되면 해당 컴포저블의 위치는 좌표 기반이 아니라 비율로 정의된다.
 *
 *  4. 제약 편향
 *    만약 반대 제약이 동일하면 해당 위젯은 축을 따라 중앙에 배치된다.
 *    반대 제약 상태에서 컴포넌트의 위치 조정을 허용하기 위해선, ConstraintLayout 에서
 *    제약 편향(constraint bias) 라고 불리는 피처를 구현해야 한다.
 *    제약 편향은 축을 따라 컴포저블의 위치를 지정해 하나의 제약 조건에 대해 지정된 백분율 만큼 조정할 수 있다.
 *
 *  5. 체인
 *    하나의 그룹으로 정의된 2개 이상의 컴포저블을들의 간격과 크기를 정의한다.
 *    이 때, 수직 체인에선 가장 위의 요소가, 수평 체인에서는 가장 왼쪽 요소를 체인 헤드라고 한다.
 *
 *  6. 체인 스타일
 *    체인의 레이아웃 동작은 체인 헤드 컴포저블에 적용된 체인 스타일 설정에 따라 정의된다.
 *    spread chain : 컴포저블들은 이용 가능한 공간에 공평하게 분배된다.(default)
 *    spread inside chain : 체인 헤드와 체인의 마지막 위젯 사이에 공평하게 분배된다.(양 끝에 붙어서 배치)
 *    weight chain : 공간을 weighting 프로퍼티를 통해 정의된다.
 *    packed chain : 사이에 여유 공간 없이 위치한다.
 *
 *  7. 가이드라인 헬퍼
 *    추가적으로 연결 가능한 제약을 제공한다.
 *
 *  8. 배리어 헬퍼
 *    가상의 뷰로 컴포저블들을 레이아웃 안에 표시되도록 제한할 때 사용한다.
 *    가이드라인과 유사하지만 레이아웃 안에서 고정된 위치에 남아있는 가이드 라인과 달리 베리어의 위치는
 *    레퍼런스 컴포넌트에 의해 정의된다.
 * */
class Chap30Activity : ComponentActivity() {
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
fun ConstScreen() {
    ConstraintLayout(Modifier.size(500.dp, 500.dp)) {
        val (button1, button2, button3, button4) = createRefs() // 참조를 생성

        MyButton(text = "Button1", Modifier.constrainAs(button1) {
            top.linkTo(parent.top, margin = 60.dp)
            //start.linkTo(parent.start) // 반대
            //end.linkTo(parent.end) // 제약
            //linkTo(parent.start, parent.end)

            // 중앙 배치
            centerHorizontallyTo(parent)
        })

        MyButton(text = "Button2", Modifier.constrainAs(button2) {
            //start.linkTo(parent.start) // 반대
            //end.linkTo(parent.end) // 제약
            //linkTo(parent.start, parent.end)

            // 중앙 배치
            centerVerticallyTo(parent)
            top.linkTo(button1.bottom) // 버튼 1의 바탁에 위를 배치
            bottom.linkTo(parent.bottom)
        })

        MyButton(text = "Button3", Modifier.constrainAs(button3) {
            top.linkTo(button2.bottom)
            linkTo(parent.start, parent.end, bias = 0.75f)
        })

        MyButton(text = "Button4", Modifier.constrainAs(button4) {
            top.linkTo(parent.top)
            linkTo(parent.start, parent.end, startMargin = 30.dp, endMargin = 50.dp)
        })
    }
}

@Composable
fun MyButton(text: String, modifier: Modifier = Modifier){
    Button(
        onClick = {},
        modifier = modifier
    ){
        Text(text)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeBasicTheme {
        ConstScreen()
    }
}