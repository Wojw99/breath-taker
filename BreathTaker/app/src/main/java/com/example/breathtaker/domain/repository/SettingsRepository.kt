package com.example.breathtaker.domain.repository

import com.example.breathtaker.domain.model.MoodRateSetting

interface SettingsRepository {
    fun getMoodRateSettings(): Array<MoodRateSetting>
}