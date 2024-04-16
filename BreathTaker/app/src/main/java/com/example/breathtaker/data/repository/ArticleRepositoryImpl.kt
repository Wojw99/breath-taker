package com.example.breathtaker.data.repository

import com.example.breathtaker.data.remote.BreathTakerAPI
import com.example.breathtaker.data.remote.dto.ArticleDto
import com.example.breathtaker.data.remote.dto.ArticlesDto
import com.example.breathtaker.domain.repository.ArticleRepository

class ArticleRepositoryImpl(
    private val api: BreathTakerAPI
) : ArticleRepository {
    override suspend fun getArticles(): ArticlesDto {
        return api.getArticles()
    }

    override suspend fun getArticleById(articleId: String): ArticleDto {
        return api.getArticleById(articleId = articleId)
    }
}