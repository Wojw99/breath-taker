package com.example.breathtaker.data.repository

import com.example.breathtaker.common.Resource
import com.example.breathtaker.common.Strings
import com.example.breathtaker.data.remote.BreathTakerAPI
import com.example.breathtaker.data.remote.dto.ArticleDto
import com.example.breathtaker.data.remote.dto.ArticlesDto
import com.example.breathtaker.data.remote.dto.toArticle
import com.example.breathtaker.data.remote.dto.toArticles
import com.example.breathtaker.domain.model.Article
import com.example.breathtaker.domain.model.Articles
import com.example.breathtaker.domain.repository.ArticleRepository
import retrofit2.HttpException
import java.io.IOException

class ArticleRepositoryImpl(
    private val api: BreathTakerAPI
) : ArticleRepository {
    override suspend fun getArticles(): Resource<Articles> {
        try {
            val articles = api.getArticles().toArticles()
            return Resource.Success(articles)
        } catch (e: HttpException) {
            return Resource.Error(e.localizedMessage ?: Strings.unexpectedErrorOccured)
        } catch (e: IOException) {
            return Resource.Error(Strings.unexpectedErrorOccured)
        }
    }

    override suspend fun getArticleById(articleId: String): Resource<Article> {
        try {
            val article = api.getArticleById(articleId = articleId).toArticle()
            return Resource.Success(article)
        } catch (e: HttpException) {
            return Resource.Error(e.localizedMessage ?: Strings.unexpectedErrorOccured)
        } catch (e: IOException) {
            return Resource.Error(Strings.unexpectedErrorOccured)
        }
    }
}