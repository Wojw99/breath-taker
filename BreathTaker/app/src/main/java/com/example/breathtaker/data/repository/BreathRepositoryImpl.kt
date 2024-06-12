package com.example.breathtaker.data.repository

import android.content.SharedPreferences
import com.example.breathtaker.common.Constants
import com.example.breathtaker.common.Resource
import com.example.breathtaker.domain.model.BreathParameters
import com.example.breathtaker.domain.repository.BreathRepository
import com.example.breathtaker.domain.repository.SettingsRepository
import kotlin.math.PI
import kotlin.math.round
import kotlin.math.sin

class BreathRepositoryImpl(
    private val sharedPreferences: SharedPreferences,
    private val settingsRepository: SettingsRepository,
): BreathRepository {
    private var moodRate: Int = 3
    private var inhalePauseDuration = 0.5
    private var exhalePauseDuration = 0.5

    override fun getInhaleProgress(time: Double, phaseDuration: Double): Double {
        val timeNorm = normalize(time, 0.0, phaseDuration, 0.0, 1.0)
        val progress = calculateProgress(timeNorm, 1.0)
        return progress
    }

    private fun calculateProgress(time: Double, duration: Double): Double {
        // Scale time to fit within one period of the sine function
        // One period of the sine function is 2 * PI
        return sin(0.5 * PI * time / duration)
    }

    private fun normalize(value: Double, minOld: Double, maxOld: Double, minNew: Double, maxNew: Double): Double {
        return minNew + (value - minOld) * (maxNew - minNew) / (maxOld - minOld)
    }

    override fun getBreathParameters(): Resource<BreathParameters> {
        val userData = settingsRepository.getUserData()

        if(userData.data == null) {
            return Resource.Error("User data not found")
        }

        val totalTime = calculateTotalTime(moodRate, 0)
        val tv = calculateTidalVolume(userData.data.height, userData.data.sex)
        val rr = calculateRespiratoryRate(moodRate, tv)
        val (inhaleTime, exhaleTime) = calculateInhaleExhaleTime(rr)

        val breathParameters = BreathParameters(
            totalTime = totalTime.toDouble(),
            exhaleDuration = exhaleTime,
            inhaleDuration = inhaleTime,
            exhalePauseDuration = exhalePauseDuration,
            inhalePauseDuration = inhalePauseDuration
        )

        return Resource.Success(breathParameters)
    }

    override fun setMoodRate(moodRate: Int) {
        this.moodRate = moodRate
    }

    private fun calculateInhaleExhaleTime(respiratoryRate: Double): Pair<Double, Double> {
        val inhaleTime = 60 / (3 * respiratoryRate)
        val exhaleTime = 2 * inhaleTime
        return Pair(inhaleTime, exhaleTime)
    }

    private fun calculateTotalTime(mood: Int, previousNumber: Int): Int {
        val prevSec = minOf(previousNumber, 90)
        val startSec = 30

        val prevSecWeight = 1
        val moodWeight = 5

        val totalTime = startSec + prevSec * prevSecWeight + mood * moodWeight
        return totalTime.toInt()
    }

    private fun calculateRespiratoryRate(mood: Int, tv: Double): Double {
        val moodMin = 1
        val rrMin = 12
        val tvMin = 260 // for 150 female

        val moodMax = 5
        val rrMax = 18
        val tvMax = 558 // for 200 male

        // Linear interpolation formula for respiratory rate and tidal volume
        val rr1 = rrMin + (tv - tvMin).toDouble() / (tvMax - tvMin) * (rrMax - rrMin)

        // Linear interpolation formula for respiratory rate and mood value
        val rr2 = rrMin + (mood - moodMin).toDouble() / (moodMax - moodMin) * (rrMax - rrMin)

        val mean = (rr1 + rr2) / 2
        return mean
    }

    private fun calculateTidalVolume(heightCm: Int, sex: String): Double{
        val heightIn = heightCm / 2.54

        val mlKg = 6.6 // 6 - 8

        val ibw = if (sex == Constants.MALE_OPTION) {
            50 + 2.3 * (heightIn - 60)
        } else {
            45.5 + 2.3 * (heightIn - 60)
        }

        val tidalVolume = (mlKg * ibw)
        return tidalVolume
    }
}