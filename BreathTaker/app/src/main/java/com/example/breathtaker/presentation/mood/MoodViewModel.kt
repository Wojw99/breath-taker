package com.example.breathtaker.presentation.mood

import androidx.lifecycle.ViewModel
import com.example.breathtaker.BreathTakerApp
import com.example.breathtaker.R
import com.example.breathtaker.domain.model.MoodRateSetting
import com.example.breathtaker.presentation.ui.theme.Colors

class MoodViewModel : ViewModel() {
    val moodRates = BreathTakerApp.appModule.settingsRepository.getMoodRateSettings()

    fun saveMoodRate(rate: Int) {
        BreathTakerApp.appModule.breathRepository.setMoodRate(rate)
    }
}
