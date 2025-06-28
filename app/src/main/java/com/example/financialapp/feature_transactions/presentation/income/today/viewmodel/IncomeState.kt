package com.example.financialapp.feature_transactions.presentation.income.today.viewmodel

import com.example.financialapp.core.network.FinResult
import com.example.financialapp.feature_bill.domain.model.AccountBriefModel
import com.example.financialapp.feature_transactions.domain.model.TransactionModel

/**
 * Состояние экрана доходов
 * */

data class IncomeState (
    val transactions: List<TransactionModel> = emptyList(),
    val accounts: List<AccountBriefModel> = emptyList(),
    val status: FinResult = FinResult.Loading,
)