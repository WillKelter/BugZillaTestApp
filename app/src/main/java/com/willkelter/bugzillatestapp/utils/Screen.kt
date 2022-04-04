package com.willkelter.bugzillatestapp.utils

//Sealed Class of Navigation routes
sealed class Screen(val route: String){
    object Home: Screen("home_screen")
    object Detail: Screen("detail_screen")
}
