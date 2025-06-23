package com.example.financialapp.feature_transactions.presentation.expenses.history

sealed class HistoryExpensesAction {
    data class ShowSnackBar(val message: String) : HistoryExpensesAction()
}
