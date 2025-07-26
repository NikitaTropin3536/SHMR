package com.example.bill.di.modules

import dagger.Module
import dagger.Provides
import com.example.account.data.AccountRepositoryImpl
import com.example.account.domain.AccountRepository
import com.example.bill.data.repository.BillRepositoryImpl
import com.example.bill.domain.repository.BillRepository
import com.example.storage.data.dao.AccountDao
import com.example.storage.data.dao.TransactionDao
import com.example.storage.data.sync.AppSyncStorage
import com.example.transactions.data.TransactionsDatasourceImpl
import com.example.transactions.domain.TransactionsDatasource

/**
 * Модуль репозиториев счета
 * */

@Module
class BillRepositoryModule {

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

    @Provides
    fun provideTransactionsDatasource(
        transactionDao: TransactionDao,
        syncStorage: AppSyncStorage
    ): TransactionsDatasource {
        return TransactionsDatasourceImpl(
            transactionDao = transactionDao,
            appSyncStorage = syncStorage
        )
    }

    @Provides
    fun provideBillRepository(): BillRepository {
        return BillRepositoryImpl()
    }

}
