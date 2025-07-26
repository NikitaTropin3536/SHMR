package com.example.storage.data.mappers.category

import com.example.common.core.model.category.CategoryModel
import com.example.storage.data.model.CategoryEntity

fun CategoryModel.toCategoryEntity(): CategoryEntity
    = CategoryEntity(
    id = id,
    name = name,
    emoji = emoji,
    isIncome = isIncome
)
