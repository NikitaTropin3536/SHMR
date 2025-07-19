package com.example.articles.di.modules

import dagger.Module
import dagger.Provides
import com.example.articles.data.ArticlesRepositoryImpl
import com.example.articles.domain.ArticlesRepository
import com.example.storage.data.dao.CategoryDao
import com.example.storage.data.sync.AppSyncStorage

/**
 * Модуль репозиториев статей
 * */

@Module
class ArticlesRepositoryModule {

    @Provides
    fun provideArticlesRepository(
        categoryDao: CategoryDao,
        syncStorage: AppSyncStorage
    ): ArticlesRepository {
        return ArticlesRepositoryImpl(
            categoryDao = categoryDao,
            appSyncStorage = syncStorage
        )
    }

}
