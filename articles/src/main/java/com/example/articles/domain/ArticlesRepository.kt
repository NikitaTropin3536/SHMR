package com.example.articles.domain

import com.example.common.core.model.CategoryModel

interface ArticlesRepository {
    suspend fun getArticles(): Result<List<CategoryModel>>
}
