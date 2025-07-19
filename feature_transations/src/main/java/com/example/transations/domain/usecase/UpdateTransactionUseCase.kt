package com.example.transations.domain.usecase

import com.example.core.network.retryRequest
import com.example.transations.data.dto.RequestTransactionDto
import com.example.transations.domain.repository.TransactionsRepository

/**
 * Use Case для обновления транзакций
 * */

class UpdateTransactionUseCase (
    private val repository: TransactionsRepository
) {
    suspend fun invoke (
        id: Int,
        transaction: RequestTransactionDto
    ): Result<Unit> {
        return retryRequest {
            repository.updateTransaction(
                id = id,
                transaction = transaction
            )
        }
    }
}
