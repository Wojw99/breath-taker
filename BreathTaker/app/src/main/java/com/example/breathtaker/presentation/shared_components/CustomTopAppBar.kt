package com.example.breathtaker.presentation.shared_components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.breathtaker.BreathTakerApp
import com.example.breathtaker.R
import com.example.breathtaker.presentation.ui.theme.Colors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    navController: NavController,
    showBackArrow: Boolean = false,
    lightTheme: Boolean = false,
    titleText: String = stringResource(id = R.string.app_name)
) {
    val moveBack = BreathTakerApp.appModule.appResources.getString(
        R.string.moveBack)

    TopAppBar(
        title = {
            Text(text = titleText)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = if (lightTheme) Colors.LightColor else Colors.MainDarkColor,
            titleContentColor = if (lightTheme) Colors.MainDarkColor else Colors.LightColor,
            navigationIconContentColor = if (lightTheme) Colors.MainDarkColor else Colors.LightColor,
        ),
        navigationIcon = {
            if (showBackArrow) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = moveBack
                    )
                }
            }
        }
    )
}