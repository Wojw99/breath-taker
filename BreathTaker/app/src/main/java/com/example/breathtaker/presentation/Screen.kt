package com.example.breathtaker.presentation

sealed class Screen(val route: String) {
    data object MainScreen: Screen("main")
    data object ArticleScreen: Screen("article")
}