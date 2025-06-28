package com.example.financialapp.feature_transactions.presentation.expenses.today.viewmodel

import com.example.financialapp.core.network.FinResult
import com.example.financialapp.feature_bill.domain.model.AccountBriefModel
import com.example.financialapp.feature_transactions.domain.model.TransactionModel

/**
 * Состояние экрана расходов
 * */

data class ExpensesState (
    val transactions: List<TransactionModel> = emptyList(),
    val accounts: List<AccountBriefModel> = emptyList(),
    val status: FinResult = FinResult.Loading,
)

