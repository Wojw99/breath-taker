package com.example.breathtaker.domain.repository

import com.example.breathtaker.domain.model.Article
import com.example.breathtaker.domain.model.Articles

interface ArticleRepository {
    suspend fun getArticles(): Articles
    suspend fun getArticleById(articleId: String): Article
}