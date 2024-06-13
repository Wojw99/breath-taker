package com.example.breathtaker.domain.repository

import com.example.breathtaker.common.Resource
import com.example.breathtaker.domain.model.AppUserBreathData
import com.example.breathtaker.domain.model.AppUserData
import com.example.breathtaker.domain.model.MoodRateSetting

interface SettingsRepository {
    fun getUserBreathData(): Resource<AppUserBreathData>
    fun setUserBreathData(userBreathData: AppUserBreathData): Resource<AppUserBreathData>
    fun getUserData(): Resource<AppUserData>
    fun setUserData(sex: String, height: String) : Resource<AppUserData>
    fun getMoodRateSettings(): Array<MoodRateSetting>
    fun setUserDataCollected(userDataCollected: Boolean)
    fun getUserDataCollected(): Boolean
}