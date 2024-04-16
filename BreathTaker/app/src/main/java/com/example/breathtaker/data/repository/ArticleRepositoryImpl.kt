package com.example.breathtaker.data.repository

import com.example.breathtaker.data.remote.BreathTakerAPI
import com.example.breathtaker.data.remote.dto.ArticleDto
import com.example.breathtaker.data.remote.dto.ArticlesDto
import com.example.breathtaker.data.remote.dto.toArticle
import com.example.breathtaker.data.remote.dto.toArticles
import com.example.breathtaker.domain.model.Article
import com.example.breathtaker.domain.model.Articles
import com.example.breathtaker.domain.repository.ArticleRepository

class ArticleRepositoryImpl(
    private val api: BreathTakerAPI
) : ArticleRepository {
    override suspend fun getArticles(): Articles {
        return api.getArticles().toArticles()
    }

    override suspend fun getArticleById(articleId: String): Article {
        return api.getArticleById(articleId = articleId).toArticle()
    }
}