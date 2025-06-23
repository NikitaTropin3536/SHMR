package com.example.financialapp.feature_transactions.presentation.income.history

sealed class HistoryIncomeAction {
    data class ShowSnackBar(val message: String) : HistoryIncomeAction()
}

