package com.example.financialapp.feature_transactions.domain.usecase

import com.example.financialapp.core.network.retryRequest
import com.example.financialapp.feature_transactions.data.dto.TransactionDto
import com.example.financialapp.feature_transactions.domain.repository.TransactionsRepository

/**
 * Use Case для добавления транзакций
 * */

class PostTransactionUseCase (
    private val repository: TransactionsRepository
) {
    suspend fun invoke (
        transaction: TransactionDto
    ): Result<Unit> {

        return retryRequest {
            repository.createExpense(transaction)
        }

    }
}

