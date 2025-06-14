package com.example.financialapp.feature_income.presentation

import com.example.financialapp.feature_expenses.domain.model.TransactionModel

data class IncomeState (
    val transactions: List<TransactionModel> = emptyList()
)