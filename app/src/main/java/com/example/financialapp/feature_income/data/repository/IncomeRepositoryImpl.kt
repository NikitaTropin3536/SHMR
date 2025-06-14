package com.example.financialapp.feature_income.data.repository

import com.example.financialapp.feature_account.domain.model.AccountBriefModel
import com.example.financialapp.feature_expenses.domain.model.CategoryModel
import com.example.financialapp.feature_expenses.domain.model.TransactionModel
import com.example.financialapp.feature_income.domain.repository.IncomeRepository

class IncomeRepositoryImpl : IncomeRepository {
    override suspend fun getTodayIncome(): List<TransactionModel> {
        /* TODO API */

        val acc = AccountBriefModel(
            id = "...",
            name = "...",
            balance = "...",
            currency = "..."
        )

        return listOf(

            TransactionModel(
                id = 0,
                account = acc,
                categoryModel = CategoryModel(
                    id = 0,
                    name = "Зарплата",
                    emoji = "null",
                    isIncome = true
                ),
                amount = "500 000 ₽",
                transactionDate = "...",
                comment = null,
                createdAt = "...",
                updatedAt = "...",
            ),

            TransactionModel(
                id = 0,
                account = acc,
                categoryModel = CategoryModel(
                    id = 0,
                    name = "Подработка",
                    emoji = "null",
                    isIncome = true
                ),
                amount = "100 000 ₽",
                transactionDate = "...",
                comment = null,
                createdAt = "...",
                updatedAt = "...",
            ),

            )
    }
}