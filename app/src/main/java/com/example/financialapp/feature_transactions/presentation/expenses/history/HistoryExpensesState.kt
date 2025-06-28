package com.example.financialapp.feature_transactions.presentation.expenses.history

import com.example.financialapp.core.network.FinResult
import com.example.financialapp.feature_bill.domain.model.AccountBriefModel
import com.example.financialapp.feature_transactions.domain.model.TransactionModel
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
        .format(DateTimeFormatter.ISO_DATE)
)