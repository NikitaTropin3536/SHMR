package com.example.financialapp.feature_expenses.domain.usecase

import com.example.financialapp.feature_expenses.data.repository.ExpensesRepositoryImpl
import com.example.financialapp.feature_expenses.domain.model.TransactionModel
import com.example.financialapp.feature_expenses.domain.repository.ExpensesRepository

class GetExpensesUseCase {

    private val repository: ExpensesRepository = ExpensesRepositoryImpl()

    suspend operator fun invoke(): List<TransactionModel> {
        return repository.getTodayExpenses()
    }

}