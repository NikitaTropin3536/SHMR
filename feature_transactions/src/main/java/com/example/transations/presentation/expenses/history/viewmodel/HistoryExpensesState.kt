package com.example.transations.presentation.expenses.history.viewmodel

import com.example.common.core.model.account.AccountBriefModel
import com.example.common.core.model.transaction.TransactionModel
import com.example.core.network.FinResult
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Состояние экрана истории расходов
 * */

data class HistoryExpensesState (
    val transactions: List<TransactionModel> = listOf(),
    val accounts: List<AccountBriefModel> = listOf(),
    val status : FinResult = FinResult.Loading,
    val startDate: String = LocalDate.now()
        .withDayOfMonth(1)
        .format(DateTimeFormatter.ISO_DATE),
    val endDate: String = LocalDate.now()
        .format(DateTimeFormatter.ISO_DATE),
    val lastSync: Long? = null
)
