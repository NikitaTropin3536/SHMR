package com.example.financialapp.feature_transactions.presentation.income.today

sealed class IncomeEvent {
    data object OnLoadTodayIncomes : IncomeEvent()
}
