package com.example.feature_splash.di.module

import dagger.Module
import dagger.Provides
import com.example.account.data.AccountRepositoryImpl
import com.example.account.domain.AccountRepository
import com.example.articles.data.ArticlesRepositoryImpl
import com.example.articles.domain.ArticlesRepository
import com.example.storage.data.dao.AccountDao
import com.example.storage.data.dao.CategoryDao
import com.example.storage.data.sync.AppSyncStorage

/**
 * Модуль репозиториев сплеш
 * */

@Module
class SplashRepositoryModule {

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

    @Provides
    fun provideAccountRepository(
        accountDao: AccountDao,
        appSyncStorage: AppSyncStorage
    ): AccountRepository {
        return AccountRepositoryImpl(
            accountDao = accountDao,
            appSyncStorage = appSyncStorage
        )
    }
}