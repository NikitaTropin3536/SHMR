package com.example.transations.domain.usecase

import com.example.common.core.model.CategoryModel
import com.example.core.network.retryRequest
import com.example.transations.domain.repository.TransactionsRepository
import jakarta.inject.Inject

/**
 * Use Case для получения статей
 * */

class GetArticlesUseCase @Inject constructor(
    private val repository: TransactionsRepository
) {

    suspend operator fun invoke(): Result<List<CategoryModel>> {

        return retryRequest {
            repository.getArticles()
        }

    }

}
