package com.example.breathtaker.domain.repository

import com.example.breathtaker.common.Resource
import com.example.breathtaker.domain.model.ArticleDetails
import com.example.breathtaker.domain.model.Articles

interface ArticleRepository {
    suspend fun getArticles(): Resource<Articles>
    suspend fun getArticleById(articleId: String): Resource<ArticleDetails>
}