package com.example.breathtaker.data.remote.dto

import com.example.breathtaker.domain.model.Article
import com.example.breathtaker.domain.model.ArticleLimited
import com.google.gson.annotations.SerializedName

data class ArticleDto(
    val sections: List<String>,
    @SerializedName("icon_name")
    val iconName: String,
    val id: Int,
    @SerializedName("text_read")
    val textRead: String,
    val title: String
)

fun ArticleDto.toArticle(): Article {
    return Article(
        title = title,
        sections = sections,
        iconName = iconName
    )
}