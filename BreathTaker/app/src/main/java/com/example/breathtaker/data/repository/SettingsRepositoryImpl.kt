package com.example.breathtaker.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.example.breathtaker.common.Constants
import com.example.breathtaker.common.Resource
import com.example.breathtaker.data.local.SettingsStorage
import com.example.breathtaker.domain.model.AppUserBreathData
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

    override fun getUserBreathData(): Resource<AppUserBreathData> {
        val inhale = sharedPreferences.getFloat(Constants.USER_DATA_INHALE_DUR_KEY, 1.5f)
        val exhale = sharedPreferences.getFloat(Constants.USER_DATA_EXHALE_DUR_KEY, 3f)
        val inhalePause = sharedPreferences.getFloat(Constants.USER_DATA_INHALE_PAUSE_DUR_KEY, 1.0f)
        val exhalePause = sharedPreferences.getFloat(Constants.USER_DATA_EXHALE_PAUSE_DUR_KEY, 1.0f)

        val userData = AppUserBreathData(
            exhaleDuration = exhale,
            inhaleDuration = inhale,
            exhalePauseDuration = exhalePause,
            inhalePauseDuration = inhalePause
        )
        return Resource.Success(userData)
    }

    override fun setUserBreathData(userBreathData: AppUserBreathData): Resource<AppUserBreathData> {
        val editor = sharedPreferences.edit()
        editor.putFloat(Constants.USER_DATA_EXHALE_DUR_KEY, userBreathData.exhaleDuration)
        editor.putFloat(Constants.USER_DATA_INHALE_DUR_KEY, userBreathData.inhaleDuration)
        editor.putFloat(
            Constants.USER_DATA_EXHALE_PAUSE_DUR_KEY,
            userBreathData.exhalePauseDuration
        )
        editor.putFloat(
            Constants.USER_DATA_INHALE_PAUSE_DUR_KEY,
            userBreathData.inhalePauseDuration
        )
        editor.apply()
        return Resource.Success(userBreathData)
    }

    override fun setUserData(sex: String, height: String): Resource<AppUserData> {
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