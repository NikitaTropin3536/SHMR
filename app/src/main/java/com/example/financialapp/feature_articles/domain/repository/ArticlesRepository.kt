package com.example.financialapp.feature_articles.domain.repository

import com.example.financialapp.feature_expenses.domain.model.CategoryModel

interface ArticlesRepository {

    suspend fun getArticles(): List<CategoryModel>

}
