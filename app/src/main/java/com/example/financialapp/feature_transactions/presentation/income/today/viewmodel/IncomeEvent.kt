package com.example.financialapp.feature_transactions.presentation.income.today.viewmodel

/**
 * События экрана доходов
 * */

sealed class IncomeEvent {
    data object OnLoadTodayIncomes : IncomeEvent()
}
