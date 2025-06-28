package com.example.financialapp.feature_articles.domain.repository

import com.example.financialapp.feature_transactions.domain.model.CategoryModel

interface ArticlesRepository {

    suspend fun getArticles(): Result<List<CategoryModel>>

}