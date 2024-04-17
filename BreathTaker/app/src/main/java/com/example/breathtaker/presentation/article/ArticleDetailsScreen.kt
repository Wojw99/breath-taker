package com.example.breathtaker.presentation.article

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.breathtaker.BreathTakerApp
import com.example.breathtaker.presentation.shared_components.CustomTopAppBar
import com.example.breathtaker.presentation.ui.theme.Colors
import com.example.breathtaker.presentation.viewModelFactory

@Composable
fun ArticleDetailsScreen(
    navController: NavController
) {
    val viewModel = viewModel<ArticleDetailsViewModel> (
        factory = viewModelFactory {
            ArticleDetailsViewModel(
                getArticleDetailsUseCase = BreathTakerApp.appModule.getArticleDetailsUseCase
            )
        }
    )
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                titleText = state.title,
                lightTheme = true,
                showBackArrow = true
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .background(color = Colors.LightColor)
            .fillMaxSize()
            .padding(paddingValues)) {

        }
    }
}