package com.example.breathtaker.di

import android.content.Context
import com.example.breathtaker.common.Constants
import com.example.breathtaker.data.remote.BreathTakerAPI
import com.example.breathtaker.data.repository.ArticleRepositoryImpl
import com.example.breathtaker.domain.repository.ArticleRepository
import com.example.breathtaker.domain.use_case.get_articles.GetArticlesUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppModule {
    val breathTakerAPI: BreathTakerAPI
    val articleRepository: ArticleRepository
    val getArticlesUseCase: GetArticlesUseCase
}

class AppModuleImpl(
    private val appContext: Context
) : AppModule{
    override val breathTakerAPI: BreathTakerAPI by lazy {
         Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BreathTakerAPI::class.java)
    }

    override val articleRepository: ArticleRepository by lazy {
        ArticleRepositoryImpl(breathTakerAPI)
    }

    override val getArticlesUseCase: GetArticlesUseCase by lazy {
        GetArticlesUseCase(articleRepository)
    }
}