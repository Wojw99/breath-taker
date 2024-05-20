package com.example.breathtaker.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import com.example.breathtaker.common.Constants

class NavigationHandler(
    private val savedStateHandle: SavedStateHandle
) {
    fun navigateToArticleWithId(navController: NavController, articleId: String) {
        savedStateHandle[Constants.PARAM_ARTICLE_ID_FOR_NAV] = articleId

        val route = Screen.ArticleDetailsScreen.route
        navController.navigate(route)
    }

    fun navigateToMainFromUserData(navController: NavController) {
        val route = Screen.MainScreen.route
        // navigate without adding current screen to backstack
        navController.navigate(route)
    }

    fun navigateToBreathWithRate(navController: NavController, rate: Int) {
        savedStateHandle[Constants.PARAM_GRADE_FOR_NAV] = rate

        val route = Screen.BreathScreen.route
        navController.navigate(route) {
            popUpTo(Screen.MainScreen.route) {
                saveState = true
            }
        }
    }

    fun readArticleIdFromSavedState(): String? {
        return savedStateHandle.get<String>(Constants.PARAM_ARTICLE_ID_FOR_NAV)
    }

    fun readRateFromSavedState(): Int? {
        return savedStateHandle.get<Int>(Constants.PARAM_GRADE_FOR_NAV)
    }
}