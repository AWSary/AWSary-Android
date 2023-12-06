package com.lzcalderaro.awsary.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lzcalderaro.awsary.ui.screen.HomeScreen
import com.lzcalderaro.awsary.ui.screen.DetailScreen

@Composable
fun SetUpNavGraph() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.HomeScreen.name) {

        composable(route = Screens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }

        composable(
            route = "${Screens.DetailScreen.name}/{id}",
            arguments = listOf(
                navArgument(name="id") {
                    type = NavType.StringType
                }
            )
        ) {
            backStackEntry ->
            val key = backStackEntry.arguments?.getString("id") ?: "0"
            DetailScreen(navController = navController, key)
        }
    }
}