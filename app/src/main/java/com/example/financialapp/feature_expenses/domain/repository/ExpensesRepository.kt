package com.example.financialapp.feature_expenses.domain.repository

import com.example.financialapp.feature_expenses.domain.model.TransactionModel

interface ExpensesRepository {

    suspend fun getTodayExpenses(): List<TransactionModel>

}