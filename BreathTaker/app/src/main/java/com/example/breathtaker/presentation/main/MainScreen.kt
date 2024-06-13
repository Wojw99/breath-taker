package com.example.breathtaker.presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.breathtaker.BreathTakerApp
import com.example.breathtaker.common.Constants
import com.example.breathtaker.R
import com.example.breathtaker.presentation.Screen
import com.example.breathtaker.presentation.CommonValues
import com.example.breathtaker.presentation.breath.BreathViewModel
import com.example.breathtaker.presentation.main.components.ArticleListItem
import com.example.breathtaker.presentation.main.components.BreathOptionsListItem
import com.example.breathtaker.presentation.shared_components.CustomTopAppBar
import com.example.breathtaker.presentation.ui.theme.Colors
import com.example.breathtaker.presentation.viewModelFactory

@Composable
fun MainScreen(
    navController: NavController
) {
    val viewModel = viewModel<MainViewModel>(
        factory = viewModelFactory {
            MainViewModel(BreathTakerApp.appModule.getArticlesUseCase)
        }
    )
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                showBackArrow = false
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .background(color = Colors.MainDarkColor)
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .padding(CommonValues.screenPadding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val headerColor = Colors.MainLighterColor80

                // * * * * BREATH OPTIONS * * * *
                Box(
                    modifier = Modifier
                        .weight(Constants.GOLDEN_PROPORTION)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Column {
                        Text(
                            text = stringResource(id = R.string.breathOptionsHeader),
                            color = headerColor,
                            fontSize = CommonValues.h2FontSize
                        )
                        BreathOptionsListItem(
                            text = stringResource(id = R.string.breathButtonText1),
                            subText = stringResource(id = R.string.breathButtonSubtext1),
                            onItemClick = {
                                BreathViewModel.customTraining = false
                                navController.navigate(Screen.MoodScreen.route)
                            }
                        )
                        BreathOptionsListItem(
                            text = stringResource(id = R.string.breathButtonText2),
                            subText = stringResource(id = R.string.breathButtonSubtext2),
                            onItemClick = {
                                BreathViewModel.customTraining = true
                                navController.navigate(Screen.MoodScreen.route)
                            }
                        )
                        BreathOptionsListItem(
                            text = stringResource(id = R.string.adjustButtonText),
                            subText = stringResource(id = R.string.adjustButtonSubtext),
                            onItemClick = {
                                navController.navigate(Screen.BreathAdjustmentScreen.route)
                            }
                        )
                    }
                }

                // * * * * ARTICLE LIST * * * *
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = state.articlesHeader,
                            color = headerColor,
                            fontSize = CommonValues.h2FontSize
                        )
                        ArticleList(state = state, navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun BreathButton(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(CommonValues.roundedCornerSize))
            .background(color = Colors.MainColor)
            .width(256.dp)
            .height(286.dp)
            .clickable {
                navController.navigate(Screen.MoodScreen.route)
            }
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.breathButtonText1),
                color = Colors.LightColor,
                fontSize = CommonValues.buttonTextFontSize
            )
            Spacer(modifier = Modifier.height(32.dp))
            val painter: Painter = painterResource(id = R.drawable.lungs)
            Image(
                painter = painter,
                modifier = Modifier
                    .width(140.dp)
                    .height(140.dp),
                contentDescription = stringResource(id = R.string.breathButtonText1)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(id = R.string.breathButtonSubtext1),
                color = Colors.LightColor50,
                fontSize = CommonValues.buttonSubtextFontSize
            )
        }
    }
}

@Composable
fun ArticleList(
    state: MainState,
    navController: NavController
) {
    if (!state.isLoading && state.error.isBlank()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.articles) { article ->
                ArticleListItem(
                    articleLimited = article,
                    onItemClick = {
                        val navHandler = BreathTakerApp.appModule.navigationHandler
                        navHandler.navigateToArticleWithId(
                            navController,
                            it.id.toString()
                        )
                    }
                )
            }
        }
    }
    if (state.error.isNotBlank()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = state.error, color = Colors.LightColor)
        }
    }
    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Colors.LightColor)
        }
    }
}