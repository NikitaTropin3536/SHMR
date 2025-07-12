package com.example.transations.domain.repository

import com.example.common.core.model.AccountBriefModel
import com.example.common.core.model.CategoryModel
import com.example.transations.data.dto.TransactionDto
import com.example.transations.domain.model.TransactionModel

interface TransactionsRepository {

    suspend fun getTransactions(
        accountId : Int,
        startDate: String,
        endDate: String
    ): Result<List<TransactionModel>>

    suspend fun getAccounts(): Result<List<AccountBriefModel>>

    suspend fun createTransaction(
        transaction: TransactionDto
    ): Result<Unit>

    suspend fun updateTransaction(
        id: Int,
        transaction: TransactionDto
    ): Result<Unit>

    suspend fun deleteTransaction(
        id: Int
    ): Result<Unit>

    suspend fun getArticles(): Result<List<CategoryModel>>

}