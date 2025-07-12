package com.example.transations.domain.usecase

import com.example.core.network.retryRequest
import com.example.transations.data.dto.TransactionDto
import com.example.transations.domain.repository.TransactionsRepository

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
            repository.createTransaction(transaction)
        }

    }
}
