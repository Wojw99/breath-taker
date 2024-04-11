package com.example.breathtaker.data.remote.dto

import com.example.breathtaker.domain.model.Articles
import com.google.gson.annotations.SerializedName

data class ArticlesDto(
    val articles: List<ArticleLimitedDto>,
    @SerializedName("articles_header")
    val articlesHeader: String
)

fun ArticlesDto.toArticles(): Articles {
    return Articles(
        articles = articles.map { it.toArticleLimited() },
        articlesHeader = articlesHeader
    )
}