package com.example.breathtaker.presentation.mood

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.breathtaker.presentation.shared_components.CustomTopAppBar
import com.example.breathtaker.presentation.ui.theme.Colors
import com.example.breathtaker.presentation.viewModelFactory

@Composable
fun MoodScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                titleText = "How are you?",
                lightTheme = false,
                showBackArrow = true
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier
            .background(color = Colors.MainDarkColor)
            .fillMaxSize()
            .padding(paddingValues)) {

        }
    }
}