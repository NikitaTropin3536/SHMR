package com.example.financialapp.feature_transactions.presentation.expenses.create.viewmodel

import com.example.financialapp.feature_transactions.domain.model.CategoryModel

/**
 * События экрана добавления расходов
 * */

sealed class CreateExpensesEvent {
    data class OnChoseArticle(val article: CategoryModel) : CreateExpensesEvent()
    data class OnEnterSum(val sum: String) : CreateExpensesEvent()
    data class OnEnterDate(val date: String) : CreateExpensesEvent()
    data class OnEnterTime(val time: String) : CreateExpensesEvent()
    data class OnEnterComment(val comment: String) : CreateExpensesEvent()
    data object OnLoadData : CreateExpensesEvent()
    data object OnSave : CreateExpensesEvent()
}