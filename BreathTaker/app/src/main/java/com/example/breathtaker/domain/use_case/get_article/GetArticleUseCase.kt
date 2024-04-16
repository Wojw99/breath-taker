package com.example.breathtaker.domain.use_case.get_article

import com.example.breathtaker.common.Resource
import com.example.breathtaker.common.Strings
import com.example.breathtaker.data.remote.dto.toArticle
import com.example.breathtaker.domain.model.Article
import com.example.breathtaker.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetArticleUseCase constructor(
    private val repository: ArticleRepository
) {
    operator fun invoke(articleId: String): Flow<Resource<Article>> = flow {
        emit(Resource.Loading())
        val articleResource = repository.getArticleById(articleId)
        emit(articleResource)
    }
}