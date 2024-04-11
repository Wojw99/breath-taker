package com.example.breathtaker.presentation.main


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breathtaker.common.Resource
import com.example.breathtaker.common.Strings
import com.example.breathtaker.domain.use_case.get_articles.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
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
                        articlesHeader = result.data?.articlesHeader ?: Strings.empty
                    )
                }
                is Resource.Error -> {
                    _state.value = MainState(error = result.message ?: Strings.unexpectedErrorOccured)
                }
                is Resource.Loading -> {
                    _state.value = MainState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}