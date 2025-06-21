package com.example.financialapp.feature_transactions.presentation.expenses.today

sealed class ExpensesAction {
    data class ShowSnackBar(val message: String) : ExpensesAction()
}