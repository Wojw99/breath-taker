package com.example.breathtaker.data.repository

import com.example.breathtaker.BreathTakerApplication
import com.example.breathtaker.R
import com.example.breathtaker.common.ErrorHelper
import com.example.breathtaker.common.Resource
import com.example.breathtaker.data.remote.BreathTakerAPI
import com.example.breathtaker.data.remote.dto.toArticleDetails
import com.example.breathtaker.data.remote.dto.toArticles
import com.example.breathtaker.domain.model.ArticleDetails
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
            return Resource.Error(e.localizedMessage ?: ErrorHelper.unexpectedServerErrorText)
        } catch (e: IOException) {
            return Resource.Error(ErrorHelper.unexpectedServerErrorText)
        }
    }

    override suspend fun getArticleById(articleId: String): Resource<ArticleDetails> {
        try {
            val article = api.getArticleById(articleId = articleId).toArticleDetails()
            return Resource.Success(article)
        } catch (e: HttpException) {
            return Resource.Error(e.localizedMessage ?: ErrorHelper.unexpectedServerErrorText)
        } catch (e: IOException) {
            return Resource.Error(ErrorHelper.unexpectedServerErrorText)
        }
    }
}