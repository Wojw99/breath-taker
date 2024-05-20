package com.example.breathtaker.presentation

sealed class Screen(val route: String) {
    data object MainScreen: Screen("main")
    data object ArticleDetailsScreen: Screen("articleDetails")
    data object BreathScreen: Screen("breath")
    data object MoodScreen: Screen("mood")
    data object UserDataScreen: Screen("userData")
}