package com.example.breathtaker.domain.model

data class BreathParameters(
    val totalTime: Double,
    val exhaleDuration: Double,
    val inhaleDuration: Double,
    val exhalePauseDuration: Double,
    val inhalePauseDuration: Double
) {
    companion object {
        fun getDefaultInstance(): BreathParameters {
            return BreathParameters(
                totalTime = 30.0,
                exhaleDuration = 3.0,
                inhaleDuration = 3.0,
                exhalePauseDuration = 1.0,
                inhalePauseDuration = 1.0
            )
        }
    }
}
