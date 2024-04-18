package com.example.breathtaker.presentation.mood

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.breathtaker.BreathTakerApp
import com.example.breathtaker.R
import com.example.breathtaker.common.Constants
import com.example.breathtaker.presentation.CommonValues
import com.example.breathtaker.presentation.NavigationHelpers
import com.example.breathtaker.presentation.article.ArticleDetailsViewModel
import com.example.breathtaker.presentation.shared_components.CustomTopAppBar
import com.example.breathtaker.presentation.ui.theme.Colors
import com.example.breathtaker.presentation.viewModelFactory

@Composable
fun MoodScreen(
    navController: NavController
) {
    val viewModel = viewModel<MoodViewModel>(
        factory = viewModelFactory {
            MoodViewModel()
        }
    )

    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                titleText = stringResource(id = R.string.howDoYouFeel),
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
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = CommonValues.screenPadding)
                    .fillMaxSize()
            ) {
                item {
//                    Text(
//                        text = stringResource(id = R.string.howDoYouFeel),
//                        fontSize = CommonValues.h1FontSize,
//                        color = Colors.LightColor
//                    )
                    Spacer(modifier = Modifier.height(CommonValues.screenPadding))
                }
                items(viewModel.moodRates) { moodRate ->
                    val rowHeight = 56.dp
                    Box(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(CommonValues.roundedCornerSize))
                            .fillMaxWidth()
                            .height(rowHeight)
                            .background(color = Colors.MainColor)
                            .clickable {
                                NavigationHelpers.navigateToBreathWithRate(
                                    navController = navController,
                                    rate = moodRate.rate
                                )
                            }
                    ) {
                        Row {
                            Box(
                                modifier = Modifier
                                    .clip(
                                        shape = RoundedCornerShape(
                                            topEnd = 0.dp,
                                            topStart = CommonValues.roundedCornerSize,
                                            bottomEnd = 0.dp,
                                            bottomStart = CommonValues.roundedCornerSize
                                        )
                                    )
                                    .background(color = moodRate.color)
                                    .size(rowHeight)
                            ) {
                                Text(
                                    text = moodRate.rate.toString(),
                                    color = Colors.LightColor,
                                    fontSize = CommonValues.h1FontSize,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(rowHeight)
                            ) {
                                Text(
                                    text = moodRate.text,
                                    color = Colors.LightColor,
                                    fontSize = CommonValues.buttonTextFontSize,
                                    modifier = Modifier.align(Alignment.CenterStart)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(CommonValues.screenPadding))
                }
            }
        }
    }
}