package com.example.breathtaker.common

import com.example.breathtaker.BreathTakerApplication
import com.example.breathtaker.R

object ErrorHelper {
    val unexpectedServerErrorText = BreathTakerApplication.appModule.appResources.getString(R.string.unexpectedServerErrorOccurred)
    val unexpectedAppErrorOccurred = BreathTakerApplication.appModule.appResources.getString(R.string.unexpectedAppErrorOccurred)
    val couldNotReachServer = BreathTakerApplication.appModule.appResources.getString(R.string.couldNotReachServer)
}