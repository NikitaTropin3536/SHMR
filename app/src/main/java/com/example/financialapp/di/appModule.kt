package com.example.financialapp.di

import com.example.financialapp.feature_account.data.repository.GetAccountInfoRepositoryImpl
import com.example.financialapp.feature_account.domain.repository.GetAccountInfoRepository
import com.example.financialapp.feature_account.domain.usecase.GetAccountInfoUseCase
import com.example.financialapp.feature_account.presentation.AccountViewModel
import com.example.financialapp.feature_transactions.data.db.AccountRepositoryImpl
import com.example.financialapp.feature_transactions.data.repository.TransactionsRepositoryImpl
import com.example.financialapp.feature_transactions.domain.repository.TransactionsRepository
import com.example.financialapp.feature_transactions.domain.usecase.GetAccountUseCase
import com.example.financialapp.feature_transactions.presentation.income.history.HistoryIncomeViewModel
import com.example.financialapp.feature_transactions.presentation.expenses.today.ExpensesViewModel
import com.example.financialapp.feature_transactions.presentation.income.today.IncomeViewModel
import com.example.financialapp.feature_articles.presentation.ArticlesViewModel
import com.example.financialapp.feature_transactions.presentation.expenses.history.HistoryExpensesViewModel
import com.example.financialapp.feature_transactions.presentation.expenses.create.CreateExpensesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    single { AccountRepositoryImpl(androidContext()) }
    single<TransactionsRepository> { TransactionsRepositoryImpl() }
    factory { GetAccountUseCase(get(), get()) }

    single<GetAccountInfoRepository> { GetAccountInfoRepositoryImpl() }
    factory { GetAccountInfoUseCase(get()) }

    viewModelOf(::ExpensesViewModel)
    viewModelOf(::IncomeViewModel)
    viewModelOf(::ArticlesViewModel)
    viewModelOf(::HistoryExpensesViewModel)
    viewModelOf(::HistoryIncomeViewModel)
    viewModelOf(::CreateExpensesViewModel)
    viewModelOf(::AccountViewModel)

    viewModel { AccountViewModel(get()) }
}
