package com.tencent.compose1.ui.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.tencent.compose1.ui.screens.HomeScreen
import com.tencent.compose1.ui.screens.SettingScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavHostApp() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController = navController, startDestination = Destinations.Home.route) {
        // Home
        composable(Destinations.Home.route) {
            HomeScreen {
                navController.navigate(Destinations.Setting.route)
            }
        }
        // Setting
        composable(Destinations.Setting.route) {
            SettingScreen {
                navController.popBackStack()
            }
        }
    }

}

sealed class Destinations(val route: String) {
    // 首页
    object Home: Destinations("Home")
    // 设置
    object Setting: Destinations("Setting")
}