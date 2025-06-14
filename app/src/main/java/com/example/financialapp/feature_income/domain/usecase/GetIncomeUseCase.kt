package com.example.financialapp.feature_income.domain.usecase

import com.example.financialapp.feature_expenses.domain.model.TransactionModel
import com.example.financialapp.feature_income.data.repository.IncomeRepositoryImpl

class GetIncomeUseCase {

    private val repository = IncomeRepositoryImpl()

    suspend fun invoke() : List<TransactionModel> {
        return repository.getTodayIncome()
    }
}