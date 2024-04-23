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

    companion object {
        fun getDefaultInstance(): BreathParameters {
            return BreathParameters(
                cyclesNumber = 8,
                exhaleDuration = 3f,
                inhaleDuration = 3f,
                exhalePauseDuration = 1f,
                inhalePauseDuration = 1f
            )
        }
    }
}
