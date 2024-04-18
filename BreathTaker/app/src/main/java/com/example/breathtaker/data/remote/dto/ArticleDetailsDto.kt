package com.example.breathtaker.data.remote.dto

import com.example.breathtaker.domain.model.ArticleDetails
import com.google.gson.annotations.SerializedName

data class ArticleDetailsDto(
    @SerializedName("content")
    val sections: List<String>,
    @SerializedName("icon_tag")
    val iconTag: String,
    val id: Int,
    @SerializedName("text_read")
    val textRead: String,
    val title: String
)

fun ArticleDetailsDto.toArticleDetails(): ArticleDetails {
    return ArticleDetails(
        title = title,
        sections = sections,
        iconTag = iconTag
    )
}