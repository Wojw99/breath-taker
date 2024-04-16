package com.example.breathtaker.domain.use_case.get_articles

import com.example.breathtaker.common.Resource
import com.example.breathtaker.common.Strings
import com.example.breathtaker.data.remote.dto.toArticles
import com.example.breathtaker.domain.model.Articles
import com.example.breathtaker.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetArticlesUseCase(
    private val repository: ArticleRepository
) {
    operator fun invoke(): Flow<Resource<Articles>> = flow {
        try {
            emit(Resource.Loading())
            val articles = repository.getArticles().toArticles()
            emit(Resource.Success(articles))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: Strings.unexpectedErrorOccured))
        } catch (e: IOException) {
            emit(Resource.Error(Strings.unexpectedErrorOccured))
        }
    }
}