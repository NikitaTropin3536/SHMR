package com.example.financialapp.feature_expenses.domain.model

data class CategoryModel (
    val id: Int,
    val name: String,
    val emoji: String,
    val isIncome: Boolean
)