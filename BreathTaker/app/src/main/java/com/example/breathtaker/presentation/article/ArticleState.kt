package com.example.breathtaker.presentation.article

import com.example.breathtaker.common.Strings
import com.example.breathtaker.domain.model.Article
import com.example.breathtaker.domain.model.ArticleLimited
import com.example.breathtaker.domain.model.Articles

data class ArticleState (
    val isLoading: Boolean = false,
    val title: String = Strings.empty,
    val sections: List<String> = emptyList(),
    val iconName: String = Strings.empty,
    val error: String = Strings.empty
)