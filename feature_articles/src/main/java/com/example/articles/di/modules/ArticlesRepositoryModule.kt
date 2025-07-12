package com.example.articles.di.modules

import dagger.Module
import dagger.Provides
import com.example.articles.domain.repository.ArticlesRepository
import com.example.articles.repository.ArticlesRepositoryImpl

/**
 * Модуль репозиториев статей
 * */

@Module
class ArticlesRepositoryModule {

    @Provides
    fun provideArticlesRepository(): ArticlesRepository {
        return ArticlesRepositoryImpl()
    }

}
