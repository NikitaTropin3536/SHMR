package com.example.transations.di.module

import dagger.Module
import dagger.Provides
import com.example.transations.data.repository.TransactionsRepositoryImpl
import com.example.transations.domain.repository.TransactionsRepository

/**
 * Модуль репозиториев траназакций
 * */

@Module
class TransactionsRepositoryModule {

    @Provides
    fun provideTransactionsRepository(): TransactionsRepository {
        return TransactionsRepositoryImpl()
    }

}
