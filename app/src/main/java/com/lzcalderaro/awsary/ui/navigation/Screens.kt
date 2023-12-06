package com.lzcalderaro.awsary.ui.navigation

enum class Screens {
    HomeScreen,
    DetailScreen;

    companion object {
        fun fromRoute(route: String?): Screens = when(route?.substringBefore("/")) {
            HomeScreen.name -> HomeScreen
            DetailScreen.name -> DetailScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}