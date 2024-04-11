package com.example.breathtaker.di

import com.example.breathtaker.common.Constants
import com.example.breathtaker.data.remote.BreathTakerAPI
import com.example.breathtaker.data.repository.ArticleRepositoryImpl
import com.example.breathtaker.domain.repository.ArticleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideBreathTakerAPI(): BreathTakerAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BreathTakerAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideArticleRepository(api: BreathTakerAPI): ArticleRepository {
        return ArticleRepositoryImpl(api)
    }
}