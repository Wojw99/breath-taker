package com.example.breathtaker.domain.use_case.get_articles

import com.example.breathtaker.common.Resource
import com.example.breathtaker.domain.model.Articles
import com.example.breathtaker.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetArticlesUseCase(
    private val repository: ArticleRepository
) {
    operator fun invoke(): Flow<Resource<Articles>> = flow {
        emit(Resource.Loading())
        val articlesResource = repository.getArticles()
        emit(articlesResource)
    }
}