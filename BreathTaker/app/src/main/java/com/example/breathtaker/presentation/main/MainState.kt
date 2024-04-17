package com.example.breathtaker.presentation.main

import com.example.breathtaker.domain.model.ArticleLimited

data class MainState (
    val isLoading: Boolean = false,
    val articles: List<ArticleLimited> = emptyList(),
    val articlesHeader: String = String(),
    val error: String = String()
)