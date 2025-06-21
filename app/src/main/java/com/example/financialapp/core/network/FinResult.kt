package com.example.financialapp.core.network

sealed class FinResult {
    data object Loading : FinResult()
    data object Success : FinResult()
    data object Error : FinResult()
}
