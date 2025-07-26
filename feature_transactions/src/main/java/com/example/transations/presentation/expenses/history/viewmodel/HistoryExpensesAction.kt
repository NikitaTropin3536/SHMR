package com.example.transations.presentation.expenses.history.viewmodel

/**
 * Действия со стороны VM на экран истории расходов
 * */

sealed class HistoryExpensesAction {
    data class ShowSnackBar(val message: String) : HistoryExpensesAction()
}
