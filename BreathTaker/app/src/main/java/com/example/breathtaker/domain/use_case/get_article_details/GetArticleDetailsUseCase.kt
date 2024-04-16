package com.example.breathtaker.domain.use_case.get_article_details

import com.example.breathtaker.common.Resource
import com.example.breathtaker.domain.model.ArticleDetails
import com.example.breathtaker.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetArticleDetailsUseCase constructor(
    private val repository: ArticleRepository
) {
    operator fun invoke(articleId: String): Flow<Resource<ArticleDetails>> = flow {
        emit(Resource.Loading())
        val articleResource = repository.getArticleById(articleId)
        emit(articleResource)
    }
}