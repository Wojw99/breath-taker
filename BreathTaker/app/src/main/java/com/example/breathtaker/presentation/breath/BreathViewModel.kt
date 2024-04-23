package com.example.breathtaker.presentation.breath

import android.util.Log
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.breathtaker.BreathTakerApp
import com.example.breathtaker.domain.model.BreathParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import kotlinx.coroutines.*

class BreathViewModel : ViewModel() {
    private var breathParameters = BreathParameters.getDefaultInstance()
    private var job: Job? = null
    private val timerDelay = 10L

    private val exerciseStartTime = LocalTime.now()
    private val exerciseEndTime =
        exerciseStartTime.plusSeconds(breathParameters.countExerciseDuration().toLong())
    private var phaseStartTime = LocalTime.now()
    private var phaseEndTime = phaseStartTime.plusSeconds(breathParameters.inhaleDuration.toLong())

    val statePopBack = mutableStateOf(false)
    val statePhase = mutableStateOf(PhaseState(phase = 0, progress = 0f))
    val stateExercise = mutableStateOf(ExerciseState(0f, 1, 1))

    init {
        breathParameters = BreathTakerApp.appModule.breathRepository.getBreathParameters()
        startTimer()
    }

    private fun startTimer() {
        job = viewModelScope.launch(Dispatchers.Default) {
            while (isActive) { // Keep running until the ViewModel is cleared
                withContext(Dispatchers.Main) {
                    updateExerciseState()
                    updatePhaseState()
                }
                delay(timerDelay)
            }
        }
    }

    private fun updatePhaseState() {
        val phaseHiddenProgress = calculateProgress(phaseStartTime, phaseEndTime)

        if (phaseHiddenProgress >= 1f) {
            val newPhase = getIncrementedPhase(statePhase.value.phase)
            val (startTime, endTime) = getProgressIntervals(newPhase)
            val newProgress = 0f
            statePhase.value = PhaseState(phase = newPhase, progress = newProgress)
            phaseStartTime = startTime
            phaseEndTime = endTime
        } else {
            when(val phase = statePhase.value.phase) {
                0 -> statePhase.value = PhaseState(phase, phaseHiddenProgress)
                1 -> statePhase.value = PhaseState(phase, 1f)
                2 -> statePhase.value = PhaseState(phase, 1 - phaseHiddenProgress)
                3 -> statePhase.value = PhaseState(phase, 0f)
                else -> statePhase.value = PhaseState(phase, 1f)
            }
        }
    }

    private fun getIncrementedPhase(currentPhase: Int): Int {
        if(currentPhase == 3) return 0
        return currentPhase + 1
    }

    private fun getProgressIntervals(phase: Int): Pair<LocalTime, LocalTime> {
        val startTime = LocalTime.now()

        val duration = when (phase) {
            0 -> breathParameters.inhaleDuration
            1 -> breathParameters.inhalePauseDuration
            2 -> breathParameters.exhaleDuration
            3 -> breathParameters.exhalePauseDuration
            else -> 1f
        }

        val endTime = startTime.plusSeconds(duration.toLong())
        return Pair(startTime, endTime)
    }

    private fun updateExerciseState() {
        val exerciseProgress = calculateProgress(exerciseStartTime, exerciseEndTime)

        if (exerciseProgress >= 1f) {
            statePopBack.value = true
            job?.cancel()
        } else {
            val (minutes, seconds) = calculateExerciseRemainingTime()
            stateExercise.value = ExerciseState(exerciseProgress, minutes, seconds)
        }
    }

    private fun calculateExerciseRemainingTime(): Pair<Int, Int> {
        val currentTime = LocalTime.now()
        val remainTime = ChronoUnit.SECONDS.between(currentTime, exerciseEndTime).coerceAtLeast(0L) + 1
        val minutes = remainTime / 60
        val seconds = remainTime % 60
        return Pair(minutes.toInt(), seconds.toInt())
    }

    private fun calculateProgress(startTime: LocalTime, endTime: LocalTime): Float {
        val currentTime = LocalTime.now()
        val totalTime = ChronoUnit.MILLIS.between(startTime, endTime)
        val elapsedTime = ChronoUnit.MILLIS.between(startTime, currentTime)

        if (totalTime == 0L) {
            return 1.0f
        }

        return (elapsedTime.toFloat() / totalTime.toFloat()).coerceIn(0.0f, 1.0f)
    }
}