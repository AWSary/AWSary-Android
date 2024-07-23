package com.lzcalderaro.awsary

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.android.lzcalderaro.dictionary.presentation.detail.DetailScreenRoot
import com.android.lzcalderaro.dictionary.presentation.list.ListScreenRoot


@Composable
fun NavigationRoot(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = "dictionary"
    ) {
        dictionaryGraph(navController)
    }
}

private fun NavGraphBuilder.dictionaryGraph(navController: NavHostController) {

    navigation(
        startDestination = "list",
        route="dictionary"
    ) {
        composable(route="list") {
            ListScreenRoot(
                onClickItem = { id ->
                    navController.navigate("detail/$id")
                }
            )
        }

        composable(
            route = "detail/{id}",
            arguments = listOf(
                navArgument(
                    name = "id"
                ) {
                    type = NavType.IntType
                    defaultValue = 0
                },
            )
        ) {

            val id = it.arguments?.getInt("id") ?: 0

            if (id == 0) {
                navController.navigateUp()
                return@composable
            }

            DetailScreenRoot(
                id = id,
                onBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}
