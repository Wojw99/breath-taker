package com.example.breathtaker.data.remote.dto

import com.example.breathtaker.domain.model.ArticleLimited
import com.google.gson.annotations.SerializedName

data class ArticleLimitedDto(
    @SerializedName("icon_tag")
    val iconTag: String,
    val id: Int,
    @SerializedName("text_read")
    val textRead: String,
    val title: String
)

fun ArticleLimitedDto.toArticleLimited(): ArticleLimited {
    return ArticleLimited(
        iconTag = iconTag,
        id = id,
        textRead = textRead,
        title = title
    )
}