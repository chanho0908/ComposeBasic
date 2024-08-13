package com.example.composebasic.chap47

sealed class NavRoutes(val route: String){
    object Home : NavRoutes("home")
    object Contacts : NavRoutes("contacts")
    object Favorite : NavRoutes("favorite")
}