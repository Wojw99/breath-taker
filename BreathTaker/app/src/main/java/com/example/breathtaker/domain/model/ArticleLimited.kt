package com.example.breathtaker.domain.model

import com.google.gson.annotations.SerializedName

data class ArticleLimited(
    @SerializedName("icon_name")
    val iconName: String,
    val id: Int,
    @SerializedName("text_read")
    val textRead: String,
    val title: String
)
