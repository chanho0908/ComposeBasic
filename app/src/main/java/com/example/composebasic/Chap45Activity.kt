package com.example.composebasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.composebasic.ui.theme.ComposeBasicTheme

/**
 * NavHostController : 목적지 사이의 이동, 네비게이션 스택관리 등 네비게이션
 * 관련 모든 작업을 관리하는 객체
 *
 * NavigationHost : 액티비티의 사용자 레이아웃에 추가되는 컴포넌트
 * 사용자가 이동할 목적지의 플레이스 홀더 역할.
 *
 * */
class Chap45Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBasicTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting10(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting10(name: String, modifier: Modifier = Modifier) {
    // NavController 객체 생성
    val navController = rememberNavController()

    /*
    NavHost(navController = navController,
        //startDestination = "시작경로"
    ){
        composable(Routes.Home.route)
        composable(Routes.Customer.route)
        composable(Routes.Purchase.route)
    }
    */
    Button(onClick = {
        navController.navigate(Routes.Customer.route)
    }) {
        Text(
            text = "Navigate To Customer",
            modifier = modifier
        )
    }

    Button(onClick = {
        navController.navigate(Routes.Customer.route){
            popUpTo(Routes.Home.route){
                // 네비게이션 수행 전에 목적지를 스택에서 꺼낸다.
                //inclusive = true

                // 동일한 목적지의 여러 인스턴스를 스택애 쌓지 않도록 한다.
                //launchSingleTop = true

                // 사용자가 이전에 선택했던 목적지를 선택했을 때 백 스택 항목 상태를
                // 자동으로 저장하고, 복원
//                saveState = true
//                restoreState = true
            }
        }
    }) {

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview13() {
    ComposeBasicTheme {
        Greeting10("Android")
    }
}

sealed class Routes(val route: String){
    object Home: Routes("home")
    object Customer: Routes("customer")
    object Purchase: Routes("purchase")
}