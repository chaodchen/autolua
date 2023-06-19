package com.tencent.compose1.ui.nav



sealed class Destinations(val route: String) {
    object Home: Destinations("Home")
    object Setting: Destinations("Setting")
    object About: Destinations("About")
    object Log: Destinations("Log")

}
