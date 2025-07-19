package com.example.transations.di.module

import dagger.Module
import dagger.Provides
import com.example.account.data.AccountRepositoryImpl
import com.example.account.domain.AccountRepository
import com.example.articles.data.ArticlesRepositoryImpl
import com.example.articles.domain.ArticlesRepository
import com.example.storage.data.dao.AccountDao
import com.example.storage.data.dao.CategoryDao
import com.example.storage.data.dao.TransactionDao
import com.example.storage.data.sync.AppSyncStorage
import com.example.transations.data.repository.TransactionsRepositoryImpl
import com.example.transations.domain.repository.TransactionsRepository

/**
 * Модуль репозиториев траназакций
 * */

@Module
class TransactionsRepositoryModule {

    @Provides
    fun provideTransactionsRepository(
        transactionDao: TransactionDao,
        syncStorage: AppSyncStorage
    ): TransactionsRepository {
        return TransactionsRepositoryImpl(
            transactionDao = transactionDao,
            appSyncStorage = syncStorage
        )
    }

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
