package com.example.articles.presentation.viewmodel

import com.example.common.core.model.CategoryModel
import com.example.core.network.FinResult

/**
 * Состояние экрана статей
 * */

data class ArticlesState (
    val articles: List<CategoryModel> = emptyList(),
    val searchValue: String = "",
    val status: FinResult = FinResult.Loading,
)
