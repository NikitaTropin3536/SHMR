package com.example.transations.presentation.create.viewmodel

import com.example.common.core.model.account.AccountBriefModel
import com.example.common.core.model.category.CategoryModel
import com.example.core.network.FinResult
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

/**
 * Состояние экрана добавления транзакции
 * */

data class CreateTransactionState (
    val accounts : List<AccountBriefModel> = emptyList(),
    val article: CategoryModel? = null,
    val sum: String? = null,
    val articles: List<CategoryModel> = emptyList(),
    val date: String = LocalDate.now()
        .format(DateTimeFormatter.ISO_DATE),
    val time: String = LocalTime.now()
        .format(DateTimeFormatter.ofPattern("HH:mm")),
    val comment: String? = null,
    val status: FinResult = FinResult.Loading,
    val lastSync: Long? = null
)
