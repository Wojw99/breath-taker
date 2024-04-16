package com.example.breathtaker.presentation.article

import com.example.breathtaker.common.Strings

data class ArticleState (
    val isLoading: Boolean = false,
    val title: String = Strings.empty,
    val sections: List<String> = emptyList(),
    val iconName: String = Strings.empty,
    val error: String = Strings.empty
)