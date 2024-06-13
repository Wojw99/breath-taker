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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.breathtaker.R
import com.example.breathtaker.common.IconTagMapper
import com.example.breathtaker.presentation.CommonValues
import com.example.breathtaker.presentation.breath.BreathViewModel
import com.example.breathtaker.presentation.shared_components.CustomTopAppBar
import com.example.breathtaker.presentation.ui.theme.Colors
import com.example.breathtaker.presentation.viewModelFactory
import java.time.LocalTime
import java.util.Locale

@Composable
fun BreathAdjustmentScreen(
    navController: NavController
) {
    val viewModel = viewModel<BreathAdjustmentViewModel>(
        factory = viewModelFactory {
            BreathAdjustmentViewModel()
        }
    )

    if (viewModel.state.value.popBack) {
        navController.popBackStack()
        viewModel.state.value.popBack = false
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
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxSize()
            ) {
                // * * * * PHASE INFO * * * *
                if (viewModel.state.value.started) {
                    PhaseInfo(
                        phaseNumber = viewModel.state.value.phaseNumber,
                        seconds = viewModel.state.value.seconds,
                        millis = viewModel.state.value.millis
                    )
                } else {
                    Text(
                        text = stringResource(id = R.string.breathAdjustmentInfo),
                        color = Colors.LightColor,
                        fontSize = CommonValues.h3FontSize,
                        textAlign = TextAlign.Center,
                        lineHeight = 28.sp,
                        modifier = Modifier.padding(CommonValues.screenPadding * 2)
                    )
                }

                // * * * * BUTTON * * * *
                Box {
                    Button(
                        onClick = { viewModel.onStartStopClick() },
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Colors.MainColor,
                            contentColor = Colors.LightColor
                        ),
                        modifier = Modifier
                            .height(130.dp)
                            .width(130.dp)
                    ) {
                        Text(
                            text = if (viewModel.state.value.started) {
                                if(viewModel.state.value.phaseNumber == 4) {
                                    stringResource(id = R.string.stop).uppercase()
                                } else {
                                    stringResource(id = R.string.next).uppercase()
                                }
                            } else {
                                stringResource(id = R.string.start).uppercase()
                            },
                            fontSize = CommonValues.h1FontSize,
                            color = Colors.LightColor,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PhaseInfo(
    phaseNumber: Int,
    millis: Int,
    seconds: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        val padding = 36.dp
        Text(
            text = "${stringResource(id = R.string.phase)} $phaseNumber",
            fontSize = CommonValues.h2FontSize,
            color = Colors.MainLighterColor80,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = when (phaseNumber) {
                1 -> stringResource(id = R.string.inhale)
                3 -> stringResource(id = R.string.exhale)
                else -> stringResource(id = R.string.hold)
            },
            fontSize = CommonValues.h1FontSize,
            color = Colors.LightColor,
        )
        Spacer(modifier = Modifier.height(padding))
        val painter = when (phaseNumber) {
            1 -> painterResource(id = R.drawable.inhale)
            3 -> painterResource(id = R.drawable.exhale)
            else -> painterResource(id = R.drawable.breath_stop)
        }
        Icon(
            painter = painter,
            contentDescription = stringResource(id = R.string.icon),
            tint = Colors.LightColor,
            modifier = Modifier.size(170.dp)
        )
        Spacer(modifier = Modifier.height(padding))
        Text(
            text = String.format(Locale.getDefault(), "%02d:%02d", seconds, millis / 10),
            fontSize = CommonValues.h1FontSize * 1.3,
            color = Colors.LightColor,
        )
    }
}