package com.example.transations.di.module

import dagger.Module
import dagger.Provides
import com.example.transations.domain.repository.TransactionsRepository
import com.example.transations.domain.usecase.DeleteTransactionUseCase
import com.example.transations.domain.usecase.GetAccountUseCase
import com.example.transations.domain.usecase.GetTransactionsUseCase
import com.example.transations.domain.usecase.PostTransactionUseCase
import com.example.transations.domain.usecase.UpdateTransactionUseCase

/**
 * Модуль use-case траназакций
 * */

@Module
class TransactionsUseCaseModule {
    @Provides
    fun provideGetTransactionsUseCase(
        transactionsRepository: TransactionsRepository
    ): GetTransactionsUseCase {
        return GetTransactionsUseCase(transactionsRepository)
    }

    @Provides
    fun providePostTransactionUseCase(
        transactionsRepository: TransactionsRepository
    ): PostTransactionUseCase {
        return PostTransactionUseCase(transactionsRepository)
    }

    @Provides
    fun provideGetAccountUseCase(
        transactionsRepository: TransactionsRepository
    ): GetAccountUseCase {
        return GetAccountUseCase(transactionsRepository)
    }

    @Provides
    fun provideUpdateTransactionUseCase(
        transactionsRepository: TransactionsRepository
    ): UpdateTransactionUseCase {
        return UpdateTransactionUseCase(transactionsRepository)
    }

    @Provides
    fun provideDeleteTransactionUseCase(
        transactionsRepository: TransactionsRepository
    ): DeleteTransactionUseCase {
        return DeleteTransactionUseCase(transactionsRepository)
    }


}
