package com.example.transations.presentation.income.today.viewmodel

import com.example.common.core.model.account.AccountBriefModel
import com.example.common.core.model.transaction.TransactionModel
import com.example.core.network.FinResult

/**
 * Состояние экрана доходов
 * */

data class IncomeState (
    val transactions: List<TransactionModel> = emptyList(),
    val accounts: List<AccountBriefModel> = emptyList(),
    val status: FinResult = FinResult.Loading,
    val lastSync: Long? = null,
)
