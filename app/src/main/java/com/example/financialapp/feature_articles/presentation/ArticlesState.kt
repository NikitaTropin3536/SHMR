package com.example.financialapp.feature_articles.presentation

import com.example.financialapp.feature_expenses.domain.model.CategoryModel

data class ArticlesState (
    val articles: List<CategoryModel> = listOf(),
    val searchValue: String = ""
)
