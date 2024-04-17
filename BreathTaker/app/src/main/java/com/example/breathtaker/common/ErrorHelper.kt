package com.example.breathtaker.common

import com.example.breathtaker.BreathTakerApp
import com.example.breathtaker.R

object ErrorHelper {
    val unexpectedServerErrorText = BreathTakerApp.appModule.appResources.getString(R.string.unexpectedServerErrorOccurred)
    val unexpectedAppErrorOccurred = BreathTakerApp.appModule.appResources.getString(R.string.unexpectedAppErrorOccurred)
    val couldNotReachServer = BreathTakerApp.appModule.appResources.getString(R.string.couldNotReachServer)
}