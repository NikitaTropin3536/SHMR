package com.example.financialapp.feature_transactions.presentation.expenses.today.viewmodel

/**
 * События экрана расходов
 * */

sealed class ExpensesEvent {
    data object OnLoadTodayExpenses : ExpensesEvent()
}