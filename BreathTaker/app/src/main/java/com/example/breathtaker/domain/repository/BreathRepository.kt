package com.example.breathtaker.domain.repository

import com.example.breathtaker.common.Resource
import com.example.breathtaker.domain.model.BreathParameters
import com.example.breathtaker.domain.model.UserParameters

interface BreathRepository {
    fun getInhaleProgress(time: Double, phaseDuration: Double): Double
    fun getBreathParameters(): Resource<BreathParameters>
    fun setMoodRate(moodRate: Int)
}