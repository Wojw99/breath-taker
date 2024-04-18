package com.example.breathtaker.presentation.breath

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.breathtaker.BreathTakerApp
import com.example.breathtaker.common.ErrorHelper
import com.example.breathtaker.presentation.NavigationHelpers

class BreathViewModel: ViewModel() {
    val stateError = mutableStateOf(String())
    init {
        NavigationHelpers.readRateFromSavedState()?.let { rate ->
            BreathTakerApp.appModule.breathManager.getBreathDetails(rate)
        } ?: {
            stateError.value = ErrorHelper.unexpectedAppErrorOccurred
        }
    }
}