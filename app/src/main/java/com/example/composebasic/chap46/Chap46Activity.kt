package com.example.composebasic.chap46

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composebasic.chap46.screens.Home
import com.example.composebasic.chap46.screens.Profile
import com.example.composebasic.chap46.screens.Welcome
import com.example.composebasic.ui.theme.ComposeBasicTheme

class Chap46Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBasicTheme {
                Greeting11()
            }
        }
    }
}

@Composable
fun Greeting11() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route,
    ){
        composable(NavRoutes.Home.route) {
            Home(navController = navController)
        }
        composable(NavRoutes.Welcome.route + "/{userName}") { backStackEntry ->

            val userName = backStackEntry.arguments?.getString("userName")
            Welcome(navController = navController, userName)
        }

        composable(NavRoutes.Profile.route) {
            Profile()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview14() {
    ComposeBasicTheme {
        Greeting11()
    }
}

sealed class NavRoutes(val route: String){
    object Home : NavRoutes("home")
    object Welcome : NavRoutes("welcome")
    object Profile : NavRoutes("profile")
}