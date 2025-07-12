package com.example.articles.di.modules

import dagger.Module
import dagger.Provides
import com.example.articles.domain.repository.ArticlesRepository
import com.example.articles.domain.usecase.GetArticlesUseCase

/**
 * Модуль use-case статей
 * */

@Module
class ArticlesUseCaseModule {

    @Provides
    fun provideArticlesUseCase(
        articlesRepository: ArticlesRepository
    ): GetArticlesUseCase {
        return GetArticlesUseCase(articlesRepository)
    }

}
