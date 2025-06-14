package com.example.financialapp.feature_expenses.presentation

import com.example.financialapp.feature_expenses.domain.model.TransactionModel

data class ExpensesState (
    val transactions: List<TransactionModel> = listOf()
)
