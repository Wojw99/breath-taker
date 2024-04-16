package com.example.breathtaker.presentation.article

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breathtaker.common.Constants
import com.example.breathtaker.common.Resource
import com.example.breathtaker.common.Strings
import com.example.breathtaker.domain.use_case.get_article.GetArticleUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ArticleViewModel(
    private val getArticleUseCase: GetArticleUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(ArticleState())
    val state: State<ArticleState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_ARTICLE_ID_FOR_NAV)?.let { articleId ->
            getArticle(articleId)
        }
    }

    private fun getArticle(articleId: String) {
        getArticleUseCase(articleId).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = ArticleState(
                        title = result.data?.title ?: Strings.empty,
                        iconName = result.data?.iconName ?: Strings.noIconName,
                        sections = result.data?.sections ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = ArticleState(error = result.message ?: Strings.unexpectedErrorOccured)
                }
                is Resource.Loading -> {
                    _state.value = ArticleState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}