package com.example.breathtaker.presentation.breath_adjustment

import androidx.compose.runtime.MutableState
import java.time.LocalTime

data class BreathAdjustmentState (
    var seconds: Int = 0,
    var millis: Int = 0,
    var started: Boolean = false,
    var popBack: Boolean = false,
    var phaseNumber: Int = 1,
)