package com.example.transations.presentation.income.today.viewmodel

import com.example.common.core.model.AccountBriefModel
import com.example.core.network.FinResult
import com.example.transations.domain.model.TransactionModel

/**
 * Состояние экрана доходов
 * */

data class IncomeState (
    val transactions: List<TransactionModel> = emptyList(),
    val accounts: List<AccountBriefModel> = emptyList(),
    val status: FinResult = FinResult.Loading,
)