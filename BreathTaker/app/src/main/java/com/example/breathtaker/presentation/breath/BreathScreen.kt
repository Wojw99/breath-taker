package com.example.breathtaker.presentation.breath

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.breathtaker.R
import com.example.breathtaker.presentation.CommonValues
import com.example.breathtaker.presentation.shared_components.CustomTopAppBar
import com.example.breathtaker.presentation.ui.theme.Colors
import com.example.breathtaker.presentation.viewModelFactory

@Composable
fun BreathScreen(
    navController: NavController
) {
    val viewModel = viewModel<BreathViewModel>(
        factory = viewModelFactory {
            BreathViewModel()
        }
    )

    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                titleText = String(),
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
            val screenPadding = 42.dp
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = screenPadding)
            ) {
                val space = 24.dp

                // * * * * PHASE TEXT * * * *
                Text(
                    color = Colors.LightColor,
                    fontSize = 32.sp,
                    text = stringResource(id = R.string.inhale)
                )
                Spacer(modifier = Modifier.height(space))

                // * * * * PHASE PROGRESS * * * *
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(Colors.MainColor)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .height(150.dp)
                            .background(Colors.MainLighterColor)
                    )
                }
                Spacer(modifier = Modifier.height(space))

                // * * * * EXERCISE PROGRESS * * * *
                LinearProgressIndicator(
                    progress = 0.6f,
                    color = Colors.MainLighterColor,
                    trackColor = Colors.MainColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                )
                Text(
                    fontSize = CommonValues.buttonSubtextFontSize,
                    color = Colors.MainLighterColor,
                    text = "1 min, 12 sec left"
                )

                Spacer(modifier = Modifier.height(screenPadding))
            }
        }
    }
}