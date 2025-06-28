package com.example.financialapp.feature_transactions.presentation.expenses.history

/**
 * События экрана истории расходов
 * */

sealed class HistoryExpensesEvent {
    data object OnLoadExpenses : HistoryExpensesEvent()
    data class OnChangedStartDate(val start : String) : HistoryExpensesEvent()
    data class OnChangedEndDate(val end : String) : HistoryExpensesEvent()
}