package com.example.breathtaker.presentation.breath_adjustment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.breathtaker.R
import com.example.breathtaker.presentation.CommonValues
import com.example.breathtaker.presentation.breath.BreathViewModel
import com.example.breathtaker.presentation.shared_components.CustomTopAppBar
import com.example.breathtaker.presentation.ui.theme.Colors
import com.example.breathtaker.presentation.viewModelFactory

@Composable
fun BreathAdjustmentScreen(
    navController: NavController
) {
    val viewModel = viewModel<BreathViewModel>(
        factory = viewModelFactory {
            BreathViewModel()
        }
    )

    if (viewModel.statePopBack.value) {
        navController.popBackStack()
        viewModel.statePopBack.value = false
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                titleText = stringResource(id = R.string.breathAdjustmentTitle),
                lightTheme = false,
                showBackArrow = true
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .background(color = Colors.MainDarkColor)
                .fillMaxSize()
                .padding(paddingValues)
        ) {

        }
    }
}