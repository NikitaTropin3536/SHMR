package com.example.articles.domain.usecase

import com.example.articles.domain.ArticlesRepository
import com.example.common.core.model.CategoryModel
import com.example.core.network.retryRequest
import jakarta.inject.Inject

/**
 * Use Case для получения статей
 * */

class GetArticlesUseCase @Inject constructor(
    private val repository: ArticlesRepository
) {

    suspend operator fun invoke(): Result<List<CategoryModel>> {
        return retryRequest {
            repository.getArticles()
        }
    }

}
