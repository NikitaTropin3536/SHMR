package com.example.transations.presentation.income.history.viewmodel

import com.example.common.core.model.AccountBriefModel
import com.example.common.core.model.TransactionModel
import com.example.core.network.FinancilityResult
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Состояние экрана истории доходов
 * */

data class HistoryIncomeState (
    val transactions: List<TransactionModel> = listOf(),
    val accounts: List<AccountBriefModel> = listOf(),
    val status: FinancilityResult = FinancilityResult.Loading,
    val startDate: String = LocalDate.now()
        .withDayOfMonth(1)
        .format(DateTimeFormatter.ISO_DATE),
    val endDate: String = LocalDate.now()
        .format(DateTimeFormatter.ISO_DATE),
    val lastSync: Long? = null
)
