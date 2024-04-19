package com.example.breathtaker.presentation.mood

import androidx.lifecycle.ViewModel
import com.example.breathtaker.BreathTakerApp
import com.example.breathtaker.R
import com.example.breathtaker.domain.model.MoodRateSetting
import com.example.breathtaker.presentation.ui.theme.Colors

class MoodViewModel : ViewModel() {
    val moodRates = arrayOf(
        MoodRateSetting(rate = 1, color = Colors.Grade1Color, text = BreathTakerApp.appModule.appResources.getString(R.string.grade1Text)),
        MoodRateSetting(rate = 2, color = Colors.Grade2Color, text = BreathTakerApp.appModule.appResources.getString(R.string.grade2Text)),
        MoodRateSetting(rate = 3, color = Colors.Grade3Color, text = BreathTakerApp.appModule.appResources.getString(R.string.grade3Text)),
        MoodRateSetting(rate = 4, color = Colors.Grade4Color, text = BreathTakerApp.appModule.appResources.getString(R.string.grade4Text)),
        MoodRateSetting(rate = 5, color = Colors.Grade5Color, text = BreathTakerApp.appModule.appResources.getString(R.string.grade5Text)),
    ).reversedArray()

    fun saveMoodRate(rate: Int) {
        BreathTakerApp.appModule.breathRepository.setMoodRate(rate)
    }
}
