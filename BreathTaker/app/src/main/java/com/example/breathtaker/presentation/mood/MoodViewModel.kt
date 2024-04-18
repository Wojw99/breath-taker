package com.example.breathtaker.presentation.mood

import androidx.lifecycle.ViewModel
import com.example.breathtaker.BreathTakerApp
import com.example.breathtaker.R
import com.example.breathtaker.domain.model.MoodRate

class MoodViewModel : ViewModel() {
    val moodRates = BreathTakerApp.appModule.breathManager.getMoodRates()
}
