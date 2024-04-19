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
        var m = this.moodRate
        return BreathParameters(
            cyclesNumber = 3,
            exhaleDuration = 1f,
            inhaleDuration = 1f,
            exhalePauseDuration = 0f,
            inhalePauseDuration = 0f
        )
    }

    override fun setMoodRate(moodRate: Int) {
        this.moodRate = moodRate
    }
}