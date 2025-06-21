package com.example.financialapp.feature_transactions.presentation.income.today

sealed class IncomeAction {
    data class ShowSnackBar(val message: String) : IncomeAction()
}
