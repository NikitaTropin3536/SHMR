package com.example.financialapp.di

import com.example.financialapp.feature_articles.di.articleModule
import com.example.financialapp.feature_bill.di.billModule
import com.example.financialapp.feature_transactions.di.transactionModule
/**
 * Зависимости, инициализируемые Koin-ом
 * */

val appModule = listOf(
    billModule,
    articleModule,
    transactionModule
)
