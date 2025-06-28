package com.example.financialapp.feature_transactions.presentation.expenses.history

/**
 * Действия со стороны VM на экран истории расходов
 * */

sealed class HistoryExpensesAction {
    data class ShowSnackBar(val message: String) : HistoryExpensesAction()
}
