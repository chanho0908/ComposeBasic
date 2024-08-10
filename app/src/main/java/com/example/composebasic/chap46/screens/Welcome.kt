package com.example.composebasic.chap46.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composebasic.chap46.NavRoutes

@Composable
fun Welcome(navController: NavHostController, userName: String?) {

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Welcome, $userName", style = MaterialTheme.typography.headlineSmall)

            Spacer(modifier = Modifier.size(30.dp))

            Button(onClick = {
                navController.navigate(NavRoutes.Profile.route) {
                    popUpTo(NavRoutes.Home.route)
                }
            }) {
                Text(text = "Set up your Profile")
            }
        }
    }
}