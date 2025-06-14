package com.example.financialapp.feature_expenses.domain.model

import com.example.financialapp.feature_account.domain.model.AccountBriefModel

data class TransactionModel (
    val id: Int,
    val account: AccountBriefModel,
    val categoryModel: CategoryModel,
    val amount: String,
    val transactionDate: String,
    val comment: String?,
    val createdAt: String,
    val updatedAt: String,
)