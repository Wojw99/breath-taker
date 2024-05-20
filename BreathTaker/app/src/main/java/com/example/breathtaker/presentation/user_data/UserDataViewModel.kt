package com.example.breathtaker.presentation.user_data

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.breathtaker.BreathTakerApp
import com.example.breathtaker.common.Constants
import com.example.breathtaker.common.Resource

class UserDataViewModel : ViewModel() {
    private val settingsRepository = BreathTakerApp.appModule.settingsRepository
    private val _state = mutableStateOf(UserDataState())
    val state: State<UserDataState> = _state

    fun onHeightChanged(height: String) {
        _state.value = _state.value.copy(height = height)
    }

    fun onMaleClicked() {
        _state.value = _state.value.copy(sex = Constants.MALE_OPTION)
    }

    fun onFemaleClicked() {
        _state.value = _state.value.copy(sex = Constants.FEMALE_OPTION)
    }

    fun isFemale(): Boolean {
        return _state.value.sex == Constants.FEMALE_OPTION
    }

    fun onAcceptButtonClicked() {
        val res = settingsRepository.setUserData(sex = _state.value.sex, height = _state.value.height)

        if (res is Resource.Success) {
            _state.value = _state.value.copy(navigateToMain = true)
        } else if (res is Resource.Error) {
            _state.value = _state.value.copy(errorMessage = res.message ?: "Error")
        }
    }
}