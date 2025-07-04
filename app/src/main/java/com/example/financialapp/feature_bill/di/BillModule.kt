package com.example.financialapp.feature_bill.di

import com.example.financialapp.feature_bill.data.repository.BillRepositoryImpl
import com.example.financialapp.feature_bill.domain.repository.BillRepository
import com.example.financialapp.feature_bill.domain.usecase.GetBillInfoUseCase
import com.example.financialapp.feature_bill.domain.usecase.UpdateBillUseCase
import com.example.financialapp.feature_bill.presentation.current.viewmodel.BillViewModel
import com.example.financialapp.feature_bill.presentation.edit.viewmodel.EditBillViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val billModule = module {

    // Репозиторий
    single<BillRepository> { BillRepositoryImpl() }

    // Use-case
    single { GetBillInfoUseCase(get()) }
    factory { UpdateBillUseCase(get()) }

    // ViewModel
    viewModel { BillViewModel(get(), get()) }
    viewModel { EditBillViewModel(get(), get()) }

}