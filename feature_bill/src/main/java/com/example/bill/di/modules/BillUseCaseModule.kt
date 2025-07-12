package com.example.bill.di.modules

import dagger.Module
import dagger.Provides
import com.example.bill.domain.repository.BillRepository
import com.example.bill.domain.usecase.GetBillInfoUseCase
import com.example.bill.domain.usecase.UpdateBillUseCase

/**
 * Модуль use-case счета
 * */

@Module
class BillUseCaseModule {

    @Provides
    fun provideGetBillInfoUseCase(
        billRepository: BillRepository
    ): GetBillInfoUseCase {
        return GetBillInfoUseCase(billRepository)
    }

    @Provides
    fun provideUpdateBillUseCase(
        billRepository: BillRepository
    ): UpdateBillUseCase {
        return UpdateBillUseCase(billRepository)
    }

}
