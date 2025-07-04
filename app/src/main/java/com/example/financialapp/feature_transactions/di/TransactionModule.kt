package com.example.financialapp.feature_transactions.di

import com.example.financialapp.feature_articles.presentation.viewmodel.ArticlesViewModel
import com.example.financialapp.feature_transactions.data.db.AccountRepositoryImpl
import com.example.financialapp.feature_transactions.data.repository.TransactionsRepositoryImpl
import com.example.financialapp.feature_transactions.domain.repository.TransactionsRepository
import com.example.financialapp.feature_transactions.domain.usecase.GetAccountUseCase
import com.example.financialapp.feature_transactions.domain.usecase.GetTransactionsUseCase
import com.example.financialapp.feature_transactions.domain.usecase.PostTransactionUseCase
import com.example.financialapp.feature_transactions.presentation.expenses.create.viewmodel.CreateExpensesViewModel
import com.example.financialapp.feature_transactions.presentation.expenses.history.HistoryExpensesViewModel
import com.example.financialapp.feature_transactions.presentation.expenses.today.viewmodel.ExpensesViewModel
import com.example.financialapp.feature_transactions.presentation.income.history.viewmodel.HistoryIncomeViewModel
import com.example.financialapp.feature_transactions.presentation.income.today.viewmodel.IncomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val transactionModule = module {

    // Репозиторий
    single { AccountRepositoryImpl(androidContext()) }
    single<TransactionsRepository> { TransactionsRepositoryImpl() }


    // Use-case
    factory { GetTransactionsUseCase(get()) }
    factory { PostTransactionUseCase(get()) }
    factory { GetAccountUseCase(get(), get()) }


    // ViewModel
    viewModel { ArticlesViewModel(get()) }
    viewModel { ExpensesViewModel(get(), get()) }
    viewModel { IncomeViewModel(get(), get()) }
    viewModel { HistoryExpensesViewModel(get(), get()) }
    viewModel { HistoryIncomeViewModel(get(), get()) }
    viewModel { CreateExpensesViewModel(get(), get(), get()) }

}