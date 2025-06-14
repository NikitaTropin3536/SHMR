package com.example.financialapp.feature_articles.domain.usecase

import com.example.financialapp.feature_articles.data.repository.ArticleRepositoryImpl
import com.example.financialapp.feature_expenses.domain.model.CategoryModel

class GetArticlesUseCase {

    private val repository: ArticleRepositoryImpl = ArticleRepositoryImpl()

    suspend operator fun invoke(): List<CategoryModel> {
        return repository.getArticles()
    }

}