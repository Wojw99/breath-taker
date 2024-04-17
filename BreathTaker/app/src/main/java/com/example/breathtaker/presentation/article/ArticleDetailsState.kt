package com.example.breathtaker.presentation.article

data class ArticleDetailsState (
    val isLoading: Boolean = false,
    val title: String = String(),
    val sections: List<String> = emptyList(),
    val iconName: String = String(),
    val error: String = String()
)