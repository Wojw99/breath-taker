package com.example.breathtaker.presentation

import androidx.navigation.NavController
import com.example.breathtaker.BreathTakerApp
import com.example.breathtaker.common.Constants

object NavigationHelpers {
    fun navigateToArticleWithId(navController: NavController, articleId: String) {
        val savedStateHandle = BreathTakerApp.appModule.savedStateHandle
        savedStateHandle[Constants.PARAM_ARTICLE_ID_FOR_NAV] = articleId

        val route = Screen.ArticleDetailsScreen.route
        navController.navigate(route)
    }

    fun readArticleIdFromSavedState(): String? {
        return BreathTakerApp.appModule.savedStateHandle.get<String>(Constants.PARAM_ARTICLE_ID_FOR_NAV)
    }
}