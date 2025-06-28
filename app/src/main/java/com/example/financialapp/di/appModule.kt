package com.example.financialapp.di

import com.example.financialapp.feature_bill.data.repository.GetBillInfoRepositoryImpl
import com.example.financialapp.feature_bill.domain.usecase.GetBillInfoUseCase
import com.example.financialapp.feature_bill.presentation.viewmodel.BillViewModel
import com.example.financialapp.feature_articles.data.repository.ArticleRepositoryImpl
import com.example.financialapp.feature_articles.domain.usecase.GetArticlesUseCase
import com.example.financialapp.feature_transactions.data.db.AccountRepositoryImpl
import com.example.financialapp.feature_transactions.data.repository.TransactionsRepositoryImpl
import com.example.financialapp.feature_transactions.domain.repository.TransactionsRepository
import com.example.financialapp.feature_transactions.domain.usecase.GetAccountUseCase
import com.example.financialapp.feature_transactions.presentation.income.history.viewmodel.HistoryIncomeViewModel
import com.example.financialapp.feature_transactions.presentation.expenses.today.viewmodel.ExpensesViewModel
import com.example.financialapp.feature_transactions.presentation.income.today.viewmodel.IncomeViewModel
import com.example.financialapp.feature_articles.presentation.viewmodel.ArticlesViewModel
import com.example.financialapp.feature_bill.domain.repository.GetBillInfoRepository
import com.example.financialapp.feature_transactions.domain.usecase.GetTransactionsUseCase
import com.example.financialapp.feature_transactions.domain.usecase.PostTransactionUseCase
import com.example.financialapp.feature_transactions.presentation.expenses.history.HistoryExpensesViewModel
import com.example.financialapp.feature_transactions.presentation.expenses.create.viewmodel.CreateExpensesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * Зависимости, инициализируемые Koin-ом
 * */

val appModule = module {

    single { ArticleRepositoryImpl() }
    factory { GetArticlesUseCase(get()) }

    single { AccountRepositoryImpl(androidContext()) }
    single<TransactionsRepository> { TransactionsRepositoryImpl() }

    factory { GetTransactionsUseCase(get()) }
    factory { PostTransactionUseCase(get()) }
    factory { GetAccountUseCase(get(), get()) }

    single<GetBillInfoRepository> { GetBillInfoRepositoryImpl() }
    factory { GetBillInfoUseCase(get()) }

    viewModelOf(::ExpensesViewModel)
    viewModelOf(::IncomeViewModel)
    viewModelOf(::ArticlesViewModel)
    viewModelOf(::HistoryExpensesViewModel)
    viewModelOf(::HistoryIncomeViewModel)
    viewModelOf(::CreateExpensesViewModel)
    viewModelOf(::BillViewModel)

    viewModel { BillViewModel(get()) }
}
