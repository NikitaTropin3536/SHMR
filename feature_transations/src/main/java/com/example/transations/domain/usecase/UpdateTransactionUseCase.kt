package com.example.transations.domain.usecase

import com.example.core.network.retryRequest
import com.example.transations.data.dto.TransactionDto
import com.example.transations.domain.repository.TransactionsRepository

/**
 * Use Case для обновления транзакций
 * */

class UpdateTransactionUseCase (
    private val repository: TransactionsRepository
) {
    suspend fun invoke (
        id: Int,
        transaction: TransactionDto
    ): Result<Unit> {
        return retryRequest {
            repository.updateTransaction(
                id = id,
                transaction = transaction
            )
        }
    }
}
