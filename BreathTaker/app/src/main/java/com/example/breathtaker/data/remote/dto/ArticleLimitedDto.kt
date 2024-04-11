package com.example.breathtaker.data.remote.dto

import com.example.breathtaker.domain.model.ArticleLimited
import com.google.gson.annotations.SerializedName

data class ArticleLimitedDto(
    @SerializedName("icon_name")
    val iconName: String,
    val id: Int,
    @SerializedName("text_read")
    val textRead: String,
    val title: String
)

fun ArticleLimitedDto.toArticleLimited(): ArticleLimited {
    return ArticleLimited(
        iconName = iconName,
        id = id,
        textRead = textRead,
        title = title
    )
}