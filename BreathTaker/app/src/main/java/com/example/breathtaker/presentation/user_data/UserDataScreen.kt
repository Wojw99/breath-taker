package com.example.breathtaker.presentation.user_data

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.breathtaker.BreathTakerApp
import com.example.breathtaker.R
import com.example.breathtaker.presentation.CommonValues
import com.example.breathtaker.presentation.shared_components.CustomTopAppBar
import com.example.breathtaker.presentation.ui.theme.Colors
import com.example.breathtaker.presentation.user_data.UserDataViewModel
import com.example.breathtaker.presentation.viewModelFactory

@Composable
fun UserDataScreen(
    navController: NavController
) {
    val viewModel = viewModel<UserDataViewModel>(
        factory = viewModelFactory {
            UserDataViewModel()
        }
    )

    if (viewModel.state.value.navigateToMain) {
        BreathTakerApp.appModule.navigationHandler.navigateToMainFromUserData(navController)
        viewModel.state.value.navigateToMain = false
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                titleText = stringResource(id = R.string.userParameters),
                lightTheme = false,
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
                    .fillMaxSize()
                    .padding(CommonValues.screenPadding)
            ) {
                val padding2 = 8.dp
                val buttonColors = ButtonDefaults.buttonColors(
                    containerColor = Colors.MainColor,
                    contentColor = Colors.LightColor,
                    disabledContainerColor = Colors.MainColor,
                    disabledContentColor = Colors.LightColor,
                )
                val buttonUnfocusedColors = ButtonDefaults.buttonColors(
                    containerColor = Colors.MainDarkColor,
                    contentColor = Colors.MainColor,
                    disabledContainerColor = Colors.MainColor,
                    disabledContentColor = Colors.LightColor,
                )

                // HEIGHT
                Text(
                    text = stringResource(id = R.string.heightQuestion),
                    color = Colors.LightColor,
                    fontSize = CommonValues.h3FontSize
                )
                Spacer(modifier = Modifier.height(padding2))
                TextField(
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Colors.MainDarkColor,
                        unfocusedTextColor = Colors.MainDarkColorUnfocused,
                        focusedContainerColor = Colors.LightColor,
                        unfocusedContainerColor = Colors.LightColor,
                        disabledContainerColor = Colors.LightColor,
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.heightQuestionPlaceholder),
                            color = Colors.MainDarkColorUnfocused
                        )
                    },
                    value = viewModel.state.value.height,
                    onValueChange = { viewModel.onHeightChanged(it) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(padding2 * 2))

                // SEX
                Text(
                    text = stringResource(id = R.string.sexQuestion),
                    color = Colors.LightColor,
                    fontSize = CommonValues.h3FontSize
                )
                Spacer(modifier = Modifier.height(padding2))
                Row {
                    Button(
                        onClick = { viewModel.onFemaleClicked() },
                        colors = if (viewModel.isFemale()) buttonColors else buttonUnfocusedColors,
                    ) {
                        Text(
                            text = stringResource(id = R.string.female),
                            color = Colors.LightColor,
                            fontSize = CommonValues.buttonSubtextFontSize
                        )
                    }
                    Spacer(modifier = Modifier.width(padding2))
                    Button(
                        onClick = { viewModel.onMaleClicked() },
                        colors = if (!viewModel.isFemale()) buttonColors else buttonUnfocusedColors,
                    ) {
                        Text(
                            text = stringResource(id = R.string.male),
                            color = Colors.LightColor,
                            fontSize = CommonValues.buttonSubtextFontSize
                        )
                    }
                }

                Spacer(modifier = Modifier.height(padding2 * 2))

                // ACCEPT BUTTON
                Button(
                    onClick = { viewModel.onAcceptButtonClicked() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = buttonColors,
                ) {
                    Text(
                        text = stringResource(id = R.string.accept),
                        color = Colors.LightColor,
                        fontSize = CommonValues.buttonTextFontSize
                    )
                }
            }
        }
    }
}