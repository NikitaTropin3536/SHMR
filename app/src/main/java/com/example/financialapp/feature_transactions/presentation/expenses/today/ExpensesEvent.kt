package com.example.financialapp.feature_transactions.presentation.expenses.today

sealed class ExpensesEvent {
    data object OnLoadTodayExpenses : ExpensesEvent()
}