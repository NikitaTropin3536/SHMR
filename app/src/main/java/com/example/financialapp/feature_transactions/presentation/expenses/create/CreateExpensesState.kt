package com.example.financialapp.feature_transactions.presentation.expenses.create

import com.example.financialapp.feature_account.domain.model.AccountBriefModel
import com.example.financialapp.feature_transactions.domain.model.CategoryModel
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

data class CreateExpensesState (
    val accounts : List<AccountBriefModel> = emptyList(),
    val article: CategoryModel? = null,
    val sum: String? = null,
    val articles: List<CategoryModel> = emptyList(),
    val date: String = LocalDate.now()
        .format(DateTimeFormatter.ISO_DATE),
    val time: String = LocalTime.now()
        .format(DateTimeFormatter.ofPattern("HH:mm")),
    val comment: String? = null,
    val isLoading: Boolean = false,
)

