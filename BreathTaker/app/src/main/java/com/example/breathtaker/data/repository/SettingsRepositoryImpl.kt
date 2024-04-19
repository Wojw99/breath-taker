package com.example.breathtaker.data.repository

import com.example.breathtaker.data.local.SettingsStorage
import com.example.breathtaker.domain.model.MoodRateSetting
import com.example.breathtaker.domain.repository.SettingsRepository

class SettingsRepositoryImpl(
    private val settingsStorage: SettingsStorage
) : SettingsRepository {
    override fun getMoodRateSettings(): Array<MoodRateSetting> {
        return settingsStorage.moodRates
    }
}