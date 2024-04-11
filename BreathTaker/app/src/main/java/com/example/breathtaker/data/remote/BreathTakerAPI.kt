package com.example.breathtaker.data.remote

import com.example.breathtaker.data.remote.dto.ArticleDto
import com.example.breathtaker.data.remote.dto.ArticlesDto
import retrofit2.http.GET
import retrofit2.http.Path

interface BreathTakerAPI {
    @GET("/api/articles/main")
    suspend fun getArticles() : ArticlesDto

    @GET("/api/articles/{articleId}")
    suspend fun getArticleById(@Path("articleId") articleId: String) : ArticleDto
}