package com.example.breathtaker.presentation.article

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.breathtaker.BreathTakerApp
import com.example.breathtaker.R
import com.example.breathtaker.common.IconTagMapper
import com.example.breathtaker.presentation.CommonValues
import com.example.breathtaker.presentation.NavigationHelpers
import com.example.breathtaker.presentation.main.components.ArticleListItem
import com.example.breathtaker.presentation.shared_components.CustomTopAppBar
import com.example.breathtaker.presentation.ui.theme.Colors
import com.example.breathtaker.presentation.viewModelFactory

@Composable
fun ArticleDetailsScreen(
    navController: NavController
) {
    val viewModel = viewModel<ArticleDetailsViewModel>(
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
        Box(
            modifier = Modifier
                .background(color = Colors.LightColor)
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .padding(CommonValues.screenPadding)
            ) {
                // * * * * ARTICLE ICON * * * *
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                ) {
                    val iconId = IconTagMapper.getDrawableIdFor(state.iconName)
                    val painter = painterResource(id = iconId)
                    Icon(
                        painter = painter,
                        contentDescription = stringResource(id = R.string.icon),
                        tint = Colors.MainColor,
                        modifier = Modifier
                            .size(64.dp)
                            .align(Alignment.Center)
                    )
                }

                Spacer(modifier = Modifier.height(CommonValues.screenPadding))

                // * * * * ARTICLE SECTIONS * * * *
                Box(
                    modifier = Modifier
                        .weight(5f)
                        .fillMaxSize()
                ) {
                    SectionList(state = state)
                }
            }
        }
    }
}

@Composable
fun SectionList(
    state: ArticleDetailsState
) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
    ) {
        items(state.sections) { section ->
            Text(text = section)
            Spacer(modifier = Modifier.height(CommonValues.screenPadding))
        }
    }
    if (state.error.isNotBlank()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = state.error, color = Colors.MainDarkColor)
        }
    }
    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Colors.MainDarkColor)
        }
    }
}