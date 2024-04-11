package com.example.breathtaker.domain.repository

import com.example.breathtaker.data.remote.dto.ArticleDto
import com.example.breathtaker.data.remote.dto.ArticlesDto

interface ArticleRepository {
    suspend fun getArticles(): ArticlesDto
    suspend fun getArticleById(articleId: String): ArticleDto
}