package com.example.transations.domain.usecase

import com.example.common.core.model.transaction.TransactionModel
import com.example.core.network.retryRequest
import com.example.transactions.domain.TransactionsDatasource

/**
 * Use Case для получения транзакций пользователя
 * */

class GetTransactionsUseCase (
    private val repository: TransactionsDatasource
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