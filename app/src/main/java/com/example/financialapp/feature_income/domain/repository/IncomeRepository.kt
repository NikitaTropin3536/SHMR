package com.example.financialapp.feature_income.domain.repository

import com.example.financialapp.feature_expenses.domain.model.TransactionModel

interface IncomeRepository {

    suspend fun getTodayIncome(): List<TransactionModel>

}
