package com.example.financialapp.core.network

/**
Используется для отображения состояния загрузки, успеха или ошибки при асинхронных действиях
 */

sealed class FinResult {
    data object Loading : FinResult()
    data object Success : FinResult()
    data object Error : FinResult()
}
