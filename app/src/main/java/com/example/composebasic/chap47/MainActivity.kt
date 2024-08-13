package com.example.composebasic.chap47

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composebasic.chap47.screens.Contacts
import com.example.composebasic.chap47.screens.Favorites
import com.example.composebasic.chap47.screens.Home
import com.example.composebasic.chap47.ui.theme.ComposeBasicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeBasicTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    NavigationHost(navController)
}

@Composable
fun NavigationHost(navController: NavHostController){
    NavHost(navController = navController, startDestination = NavRoutes.Home.route){
        composable(NavRoutes.Home.route){
            Home()
        }
        composable(NavRoutes.Contacts.route){
            Contacts()
        }
        composable(NavRoutes.Favorite.route){
            Favorites()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview15() {
    ComposeBasicTheme {
        MainScreen()
    }
}