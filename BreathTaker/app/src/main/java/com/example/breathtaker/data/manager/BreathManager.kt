package com.example.breathtaker.data.manager

import android.content.SharedPreferences
import com.example.breathtaker.BreathTakerApp
import com.example.breathtaker.R
import com.example.breathtaker.domain.model.BreathDetails
import com.example.breathtaker.domain.model.MoodRate
import com.example.breathtaker.presentation.ui.theme.Colors

class BreathManagerImpl(
    sharedPreferences: SharedPreferences
): BreathManager {
    override fun getMoodRates(): Array<MoodRate> {
        return arrayOf(
            MoodRate(rate = 1, color = Colors.Grade1Color, text = BreathTakerApp.appModule.appResources.getString(R.string.grade1Text)),
            MoodRate(rate = 2, color = Colors.Grade2Color, text = BreathTakerApp.appModule.appResources.getString(R.string.grade2Text)),
            MoodRate(rate = 3, color = Colors.Grade3Color, text = BreathTakerApp.appModule.appResources.getString(R.string.grade3Text)),
            MoodRate(rate = 4, color = Colors.Grade4Color, text = BreathTakerApp.appModule.appResources.getString(R.string.grade4Text)),
            MoodRate(rate = 5, color = Colors.Grade5Color, text = BreathTakerApp.appModule.appResources.getString(R.string.grade5Text)),
        ).reversedArray()
    }

    override fun getBreathDetails(rate: Int): BreathDetails {
        return BreathDetails(5f)
    }
}

interface BreathManager {
    fun getMoodRates(): Array<MoodRate>
    fun getBreathDetails(rate: Int): BreathDetails
}