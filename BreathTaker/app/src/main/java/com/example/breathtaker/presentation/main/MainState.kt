package com.example.breathtaker.presentation.main

import com.example.breathtaker.common.Strings
import com.example.breathtaker.domain.model.ArticleLimited
import com.example.breathtaker.domain.model.Articles

data class MainState (
    val isLoading: Boolean = false,
    val articles: List<ArticleLimited> = emptyList(),
    val articlesHeader: String = Strings.empty,
    val error: String = Strings.empty
)