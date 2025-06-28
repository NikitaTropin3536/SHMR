package com.example.financialapp.feature_articles.domain.usecase

import com.example.financialapp.core.network.retryRequest
import com.example.financialapp.feature_articles.data.repository.ArticleRepositoryImpl
import com.example.financialapp.feature_transactions.domain.model.CategoryModel

/**
 * Use Case для получения статей
 * */

class GetArticlesUseCase (
    private val repository: ArticleRepositoryImpl
) {

    suspend operator fun invoke(): Result<List<CategoryModel>> {

        return retryRequest {
            repository.getArticles()
        }

    }

}