package com.example.transations.presentation.expenses.today.viewmodel

import com.example.common.core.model.account.AccountBriefModel
import com.example.core.network.FinResult
import com.example.common.core.model.transaction.TransactionModel

/**
 * состояние экрана расходов
 * */

data class ExpensesState (
    val transactions: List<TransactionModel> = emptyList(),
    val accounts: List<AccountBriefModel> = emptyList(),
    val status: FinResult = FinResult.Loading,
    val lastSync: Long? = null,
)
