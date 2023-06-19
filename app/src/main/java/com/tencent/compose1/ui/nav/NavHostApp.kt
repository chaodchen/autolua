package com.tencent.compose1.ui.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.tencent.compose1.ui.screens.AboutScreen
import com.tencent.compose1.ui.screens.HomeScreen
import com.tencent.compose1.ui.screens.LogScreen
import com.tencent.compose1.ui.screens.SettingScreen
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavHostApp() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController = navController, startDestination = Destinations.Home.route) {
        // Home
        composable(Destinations.Home.route) {
            HomeScreen(viewModel(), navController)
        }
        // Setting
        composable(Destinations.Setting.route) {
            SettingScreen {
                navController.popBackStack()
            }
        }

        composable(Destinations.Log.route) {
            LogScreen {
                navController.popBackStack()
            }
        }

        composable(Destinations.About.route) {
            AboutScreen {
                navController.popBackStack()
            }
        }
    }

}

