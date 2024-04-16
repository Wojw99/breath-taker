package com.example.breathtaker.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.breathtaker.BreathTakerApplication
import com.example.breathtaker.presentation.Screen
import com.example.breathtaker.presentation.main.components.ArticleListItem
import com.example.breathtaker.presentation.viewModelFactory

@Composable
fun MainScreen(
    navController: NavController
) {
    val viewModel = viewModel<MainViewModel> (
        factory = viewModelFactory {
            MainViewModel(BreathTakerApplication.appModule.getArticlesUseCase)
        }
    )

    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.articles) { article ->
                ArticleListItem(
                    articleLimited = article,
                    onItemClick = {
                        navController.navigate(Screen.ArticleScreen.route + "/${article.id}")
                    }
                )
            }
        }
        if(state.error.isNotBlank()) {
            Text(text = state.error)
        }
        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}