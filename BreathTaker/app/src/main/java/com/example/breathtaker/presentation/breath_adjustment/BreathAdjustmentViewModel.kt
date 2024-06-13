package com.example.breathtaker.presentation.breath_adjustment

import androidx.lifecycle.ViewModel
import com.example.breathtaker.BreathTakerApp

class BreathAdjustmentViewModel : ViewModel() {
    init {
        val settingsRepo = BreathTakerApp.appModule.settingsRepository
    }
}