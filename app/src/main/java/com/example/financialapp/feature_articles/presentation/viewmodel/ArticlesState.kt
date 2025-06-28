package com.example.financialapp.feature_articles.presentation.viewmodel

import com.example.financialapp.core.network.FinResult
import com.example.financialapp.feature_transactions.domain.model.CategoryModel

/**
 * Состояние экрана статей
 * */

data class ArticlesState(
    val articles: List<CategoryModel> = emptyList(),
    val searchValue: String = "",
    val status: FinResult = FinResult.Loading,
)
