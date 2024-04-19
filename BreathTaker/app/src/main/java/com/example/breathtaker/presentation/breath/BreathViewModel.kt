package com.example.breathtaker.presentation.breath

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.breathtaker.BreathTakerApp

class BreathViewModel: ViewModel() {
    val stateError = mutableStateOf(String())
    init {
        BreathTakerApp.appModule.breathRepository.getBreathParameters()
    }
}