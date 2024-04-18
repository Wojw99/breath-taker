package com.example.breathtaker.presentation.article

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breathtaker.common.Constants
import com.example.breathtaker.common.ErrorHelper
import com.example.breathtaker.common.Resource
import com.example.breathtaker.domain.use_case.get_article_details.GetArticleDetailsUseCase
import com.example.breathtaker.presentation.NavigationHelpers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ArticleDetailsViewModel(
    private val getArticleDetailsUseCase: GetArticleDetailsUseCase
) : ViewModel() {
    private val _state = mutableStateOf(ArticleDetailsState())
    val state: State<ArticleDetailsState> = _state

    init {
        NavigationHelpers.readArticleIdFromSavedState()?.let { articleId ->
            getArticle(articleId)
        } ?: {
            _state.value = ArticleDetailsState(error = ErrorHelper.unexpectedAppErrorOccurred)
        }
    }

    private fun getArticle(articleId: String) {
        getArticleDetailsUseCase(articleId).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = ArticleDetailsState(
                        title = result.data?.title ?: String(),
                        iconName = result.data?.iconTag ?: Constants.NO_ICON,
                        sections = result.data?.sections ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = ArticleDetailsState(error = result.message ?: ErrorHelper.unexpectedAppErrorOccurred)
                }
                is Resource.Loading -> {
                    _state.value = ArticleDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}