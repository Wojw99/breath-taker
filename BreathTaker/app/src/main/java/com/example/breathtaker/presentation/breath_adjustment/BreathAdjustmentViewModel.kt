package com.example.breathtaker.presentation.breath_adjustment

import android.os.CountDownTimer
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breathtaker.BreathTakerApp
import com.example.breathtaker.domain.model.AppUserBreathData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class BreathAdjustmentViewModel : ViewModel() {
    private var job: Job? = null
    private val timerDelay = 1L // 1ms
    private var elapsedMillis = 0
    private var executor: ScheduledExecutorService? = null
    private var timer: CountDownTimer? = null

    private var inhaleDuration = 0f
    private var inhalePauseDuration = 0f
    private var exhaleDuration = 0f
    private var exhalePauseDuration = 0f

    val state = mutableStateOf(BreathAdjustmentState())

    fun onStartStopClick() {
        if (!state.value.started) {
            state.value = BreathAdjustmentState(started = true)
            restartCountdownTimer()
        } else {
            updatePhase()
            restartCountdownTimer()
        }
    }

    private fun updatePhase() {
        when (state.value.phaseNumber) {
            1 -> inhaleDuration = elapsedMillis / 1000f
            2 -> inhalePauseDuration = elapsedMillis / 1000f
            3 -> exhaleDuration = elapsedMillis / 1000f
            4 -> {
                exhalePauseDuration = elapsedMillis / 1000f
                saveUserBreathData()
                state.value = state.value.copy(popBack = true)
            }
        }
        state.value = state.value.copy(phaseNumber = state.value.phaseNumber + 1)
        restartCountdownTimer()
    }

    private fun saveUserBreathData() {
        val settingsRepo = BreathTakerApp.appModule.settingsRepository
        val userBreathData = AppUserBreathData(
            inhaleDuration = inhaleDuration,
            inhalePauseDuration = inhalePauseDuration,
            exhaleDuration = exhaleDuration,
            exhalePauseDuration = exhalePauseDuration
        )
        settingsRepo.setUserBreathData(userBreathData)
    }

    private fun restartCountdownTimer() {
        val millisInFuture = 10000L
        elapsedMillis = 0
        timer?.cancel()
        timer = object : CountDownTimer(millisInFuture, 10) {
            override fun onTick(millisUntilFinished: Long) {
                elapsedMillis = (millisInFuture - millisUntilFinished).toInt()
                val seconds = elapsedMillis / 1000
                val millis = elapsedMillis % 1000
                state.value = state.value.copy(millis = millis, seconds = seconds)
            }

            override fun onFinish() {
                state.value = state.value.copy(started = true)
                startTimer()
            }
        }
        (timer as CountDownTimer).start()
    }

    private fun startTimer() {
        executor = Executors.newSingleThreadScheduledExecutor()
        executor?.scheduleWithFixedDelay({
            elapsedMillis++
            val seconds = elapsedMillis / 1000
            val millis = elapsedMillis % 1000
            state.value = state.value.copy(millis = millis, seconds = seconds)
        }, 0, timerDelay, TimeUnit.MILLISECONDS)
    }

    private fun stopTimer() {
        elapsedMillis = 0
        executor?.shutdown()
        executor = null
    }

    private fun restartTimer() {
        elapsedMillis = 0
        job = viewModelScope.launch(Dispatchers.Default) {
            while (isActive) { // Keep running until the ViewModel is cleared
                withContext(Dispatchers.Main) {
                    elapsedMillis += 1
                    val seconds = elapsedMillis / 1000
                    val millis = elapsedMillis % 1000
                    state.value = state.value.copy(millis = millis, seconds = seconds)
                }
                delay(timerDelay)
            }
        }
    }
}