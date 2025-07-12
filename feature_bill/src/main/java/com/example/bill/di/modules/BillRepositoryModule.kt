package com.example.bill.di.modules

import dagger.Module
import dagger.Provides
import com.example.bill.data.repository.BillRepositoryImpl
import com.example.bill.domain.repository.BillRepository

/**
 * Модуль репозиториев счета
 * */

@Module
class BillRepositoryModule {

    @Provides
    fun provideBillRepository(): BillRepository {
        return BillRepositoryImpl()
    }

}
