package com.example.breathtaker.di

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import androidx.lifecycle.SavedStateHandle
import com.example.breathtaker.common.Constants
import com.example.breathtaker.data.local.SettingsStorage
import com.example.breathtaker.data.remote.BreathTakerAPI
import com.example.breathtaker.data.repository.ArticleRepositoryImpl
import com.example.breathtaker.data.repository.BreathRepositoryImpl
import com.example.breathtaker.data.repository.SettingsRepositoryImpl
import com.example.breathtaker.domain.repository.ArticleRepository
import com.example.breathtaker.domain.repository.BreathRepository
import com.example.breathtaker.domain.repository.SettingsRepository
import com.example.breathtaker.domain.use_case.get_article_details.GetArticleDetailsUseCase
import com.example.breathtaker.domain.use_case.get_articles.GetArticlesUseCase
import com.example.breathtaker.presentation.NavigationHandler
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppModule {
    val breathTakerAPI: BreathTakerAPI
    val articleRepository: ArticleRepository
    val breathRepository: BreathRepository
    val getArticlesUseCase: GetArticlesUseCase
    val getArticleDetailsUseCase: GetArticleDetailsUseCase
    val savedStateHandle: SavedStateHandle
    val navigationHandler: NavigationHandler
    val appResources: Resources
    val sharedPreferences: SharedPreferences
    val settingsRepository: SettingsRepository
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

    override val sharedPreferences: SharedPreferences by lazy {
        appContext.getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    override val breathRepository: BreathRepository by lazy {
        BreathRepositoryImpl(sharedPreferences)
    }

    override val settingsRepository: SettingsRepository by lazy {
        SettingsRepositoryImpl(SettingsStorage())
    }

    override val articleRepository: ArticleRepository by lazy {
        ArticleRepositoryImpl(breathTakerAPI)
    }

    override val getArticlesUseCase: GetArticlesUseCase by lazy {
        GetArticlesUseCase(articleRepository)
    }

    override val getArticleDetailsUseCase: GetArticleDetailsUseCase by lazy {
        GetArticleDetailsUseCase(articleRepository)
    }

    override val savedStateHandle: SavedStateHandle by lazy {
        SavedStateHandle()
    }

    override val appResources: Resources by lazy {
        appContext.resources
    }

    override val navigationHandler: NavigationHandler by lazy {
        NavigationHandler(savedStateHandle)
    }
}