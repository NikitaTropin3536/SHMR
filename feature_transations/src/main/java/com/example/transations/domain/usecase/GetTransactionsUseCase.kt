package com.example.transations.domain.usecase

import com.example.core.network.retryRequest
import com.example.common.core.model.TransactionModel
import com.example.transations.domain.repository.TransactionsRepository

/**
 * Use Case для получения транзакций пользователя
 * */

class GetTransactionsUseCase (
    private val repository: TransactionsRepository
) {

    suspend operator fun invoke(
        id : Int,
        startDate: String,
        endDate: String
    ): Result<List<TransactionModel>> {

        return retryRequest {
            repository.getTransactions(id, startDate, endDate)
        }

    }

}