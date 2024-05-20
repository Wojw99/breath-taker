package com.example.breathtaker.presentation.user_data

import com.example.breathtaker.common.Constants


data class UserDataState (
    val height : String = String(),
    val sex : String = Constants.FEMALE_OPTION,
    val errorMessage: String = String(),
    var navigateToMain : Boolean = false
)