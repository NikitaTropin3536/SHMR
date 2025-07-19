package com.example.transations.presentation.expenses.today.viewmodel

import com.example.common.core.model.AccountBriefModel
import com.example.core.network.FinancilityResult
import com.example.common.core.model.TransactionModel

/**
 * состояние экрана расходов
 * */

data class ExpensesState (
    val transactions: List<TransactionModel> = emptyList(),
    val accounts: List<AccountBriefModel> = emptyList(),
    val status: FinancilityResult = FinancilityResult.Loading,
    val lastSync: Long? = null,
)
