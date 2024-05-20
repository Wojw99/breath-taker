package com.example.breathtaker.domain.repository

import com.example.breathtaker.common.Resource
import com.example.breathtaker.domain.model.AppUserData
import com.example.breathtaker.domain.model.MoodRateSetting

interface SettingsRepository {
    fun getUserData(): Resource<AppUserData>
    fun setUserData(sex: String, height: String) : Resource<AppUserData>
    fun getMoodRateSettings(): Array<MoodRateSetting>
    fun setUserDataCollected(userDataCollected: Boolean)
    fun getUserDataCollected(): Boolean
}