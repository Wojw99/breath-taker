package com.example.breathtaker.domain.model

data class BreathParameters(
    val cyclesNumber: Int,
    val exhaleDuration: Float,
    val inhaleDuration: Float,
    val exhalePauseDuration: Float,
    val inhalePauseDuration: Float
) {
    fun countExerciseDuration(): Float {
        return (exhaleDuration + inhaleDuration + exhalePauseDuration + inhalePauseDuration) * cyclesNumber
    }
}
