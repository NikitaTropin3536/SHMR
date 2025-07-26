package com.example.storage.data.mappers.category

import com.example.common.core.model.category.CategoryModel
import com.example.storage.data.model.CategoryEntity

fun CategoryEntity.toCategoryModel(): CategoryModel
    = CategoryModel(
        id = id,
        name = name,
        emoji = emoji,
        isIncome = isIncome
    )