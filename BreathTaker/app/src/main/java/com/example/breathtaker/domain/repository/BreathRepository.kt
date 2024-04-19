package com.example.breathtaker.domain.repository

import com.example.breathtaker.domain.model.BreathParameters
import com.example.breathtaker.domain.model.UserParameters

interface BreathRepository {
    fun getBreathParameters(): BreathParameters
    fun setMoodRate(moodRate: Int)
}