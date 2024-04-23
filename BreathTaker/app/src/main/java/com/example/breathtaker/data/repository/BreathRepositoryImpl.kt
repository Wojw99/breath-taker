package com.example.breathtaker.data.repository

import android.content.SharedPreferences
import com.example.breathtaker.domain.model.BreathParameters
import com.example.breathtaker.domain.model.UserParameters
import com.example.breathtaker.domain.repository.BreathRepository

class BreathRepositoryImpl(
    sharedPreferences: SharedPreferences
): BreathRepository {
    private var moodRate: Int = 3
    override fun getBreathParameters(): BreathParameters {
        // calculate BreathParameters based on moodRate and userParameters
        return BreathParameters.getDefaultInstance()
    }

    override fun setMoodRate(moodRate: Int) {
        this.moodRate = moodRate
    }
}