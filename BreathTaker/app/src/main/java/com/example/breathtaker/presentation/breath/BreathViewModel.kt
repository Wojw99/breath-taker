package com.example.breathtaker.presentation.breath

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

class BreathViewModel(
    private val navController: NavController
) : ViewModel() {
    private var breathParameters = BreathParameters(
        cyclesNumber = 61,
        exhaleDuration = 1f,
        inhaleDuration = 0f,
        exhalePauseDuration = 0f,
        inhalePauseDuration = 0f
    )
    private val exerciseStartTime = LocalTime.now()
    private val exerciseEndTime =
        exerciseStartTime.plusSeconds(breathParameters.countExerciseDuration().toLong())
    private var job: Job? = null
    private val timerDelay = 10L

    val stateError = mutableStateOf(String())
    val stateExercise = mutableStateOf(ExerciseState(0f, 1, 1))
    val statePopBack = mutableStateOf(false)

    init {
        breathParameters = BreathTakerApp.appModule.breathRepository.getBreathParameters()
        startTimer()
    }

    private fun startTimer() {
        job = viewModelScope.launch(Dispatchers.Default) {
            while (isActive) { // Keep running until the ViewModel is cleared
                withContext(Dispatchers.Main) {
                    updateExerciseState()
                }
                delay(timerDelay)
            }
        }
    }

    private fun updateExerciseState() {
        val exerciseProgress = calculateExerciseProgress()

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

    private fun calculateExerciseProgress(): Float {
        val currentTime = LocalTime.now()
        val totalDuration = ChronoUnit.MILLIS.between(exerciseStartTime, exerciseEndTime)
        val elapsedTime = ChronoUnit.MILLIS.between(exerciseStartTime, currentTime)

        if (totalDuration == 0L) {
            return 1.0f
        }

        return (elapsedTime.toFloat() / totalDuration.toFloat()).coerceIn(0.0f, 1.0f)
    }
}