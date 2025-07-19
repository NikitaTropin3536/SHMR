package com.example.transations.presentation.income.today.viewmodel

import com.example.common.core.model.AccountBriefModel
import com.example.common.core.model.TransactionModel
import com.example.core.network.FinancilityResult

/**
 * Состояние экрана доходов
 * */

data class IncomeState (
    val transactions: List<TransactionModel> = emptyList(),
    val accounts: List<AccountBriefModel> = emptyList(),
    val status: FinancilityResult = FinancilityResult.Loading,
    val lastSync: Long? = null,
)
