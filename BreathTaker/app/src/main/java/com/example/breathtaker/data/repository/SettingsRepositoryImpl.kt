package com.example.breathtaker.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.breathtaker.common.Constants
import com.example.breathtaker.common.Resource
import com.example.breathtaker.data.local.SettingsStorage
import com.example.breathtaker.domain.model.AppUserData
import com.example.breathtaker.domain.model.MoodRateSetting
import com.example.breathtaker.domain.repository.SettingsRepository

class SettingsRepositoryImpl(
    private val settingsStorage: SettingsStorage,
    private val sharedPreferences: SharedPreferences
) : SettingsRepository {
    override fun getMoodRateSettings(): Array<MoodRateSetting> {
        return settingsStorage.moodRates
    }

    override fun setUserData(sex: String, height: String) : Resource<AppUserData>{
        val heightInt = height.toIntOrNull() ?: return Resource.Error("Height must be a number")

        if (sex != Constants.MALE_OPTION && sex != Constants.FEMALE_OPTION) {
            return Resource.Error("Not valid gender")
        }

        val editor = sharedPreferences.edit()
        editor.putString(Constants.USER_DATA_SEX_KEY, sex)
        editor.putInt(Constants.USER_DATA_HEIGHT_KEY, heightInt)
        editor.apply()

        setUserDataCollected(true)

        val userData = AppUserData(sex = sex, height = heightInt)
        return Resource.Success(userData)
    }

    override fun getUserData(): Resource<AppUserData> {
        val sex = sharedPreferences.getString(Constants.USER_DATA_SEX_KEY, "") ?: ""
        val height = sharedPreferences.getInt(Constants.USER_DATA_HEIGHT_KEY, -1)

        if (sex == "" || height == -1) {
            return Resource.Error("User data not found")
        }

        val userData = AppUserData(sex = sex, height = height)
        return Resource.Success(userData)
    }

    override fun setUserDataCollected(userDataCollected: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(Constants.USER_DATA_COLLECTED_KEY, userDataCollected)
        editor.apply()
    }

    override fun getUserDataCollected(): Boolean {
        return sharedPreferences.getBoolean(Constants.USER_DATA_COLLECTED_KEY, false)
    }
}