package com.example.transations.domain.repository

import com.example.transactions.data.dto.RequestTransactionDto

interface TransactionsRepository {

    suspend fun createTransaction(
        transaction: RequestTransactionDto
    ): Result<Unit>

    suspend fun updateTransaction(
        id: Int,
        transaction: RequestTransactionDto
    ): Result<Unit>

    suspend fun deleteTransaction(
        id: Int
    ): Result<Unit>

}