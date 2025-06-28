package com.example.financialapp.feature_transactions.domain.usecase

import com.example.financialapp.core.network.retryRequest
import com.example.financialapp.feature_transactions.domain.model.TransactionModel
import com.example.financialapp.feature_transactions.domain.repository.TransactionsRepository

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