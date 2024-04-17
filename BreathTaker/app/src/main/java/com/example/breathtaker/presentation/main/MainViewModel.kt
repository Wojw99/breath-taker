package com.example.breathtaker.presentation.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breathtaker.common.ErrorHelper
import com.example.breathtaker.common.Resource
import com.example.breathtaker.domain.use_case.get_articles.GetArticlesUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainViewModel(
    private val getArticlesUseCase: GetArticlesUseCase
) : ViewModel() {
    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    init {
        getArticles()
    }

    private fun getArticles() {
        getArticlesUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = MainState(
                        articles = result.data?.articles ?: emptyList(),
                        articlesHeader = result.data?.articlesHeader ?: String()
                    )
                }
                is Resource.Error -> {
                    _state.value = MainState(error = result.message ?: ErrorHelper.unexpectedServerErrorText)
                }
                is Resource.Loading -> {
                    _state.value = MainState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}