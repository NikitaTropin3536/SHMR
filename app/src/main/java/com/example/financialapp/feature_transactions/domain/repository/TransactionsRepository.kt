package com.example.financialapp.feature_transactions.domain.repository

import com.example.financialapp.feature_account.domain.model.AccountBriefModel
import com.example.financialapp.feature_transactions.data.dto.TransactionDto
import com.example.financialapp.feature_transactions.domain.model.TransactionModel

interface TransactionsRepository {

    suspend fun getTransactions(
        accountId : Int,
        startDate: String,
        endDate: String
    ): Result<List<TransactionModel>>

    suspend fun getAccounts(): Result<List<AccountBriefModel>>

    suspend fun createExpense(
        transaction: TransactionDto
    ): Result<Unit>

}