package com.example.transations.domain.usecase

import com.example.core.network.retryRequest
import com.example.transations.domain.repository.TransactionsRepository

/**
 * Use Case для удаления транзакций
 * */

class DeleteTransactionUseCase (
    private val repository: TransactionsRepository
) {
    suspend fun invoke (
        id: Int,
    ): Result<Unit> {
        return retryRequest {
            repository.deleteTransaction(
                id = id,
            )
        }
    }
}
