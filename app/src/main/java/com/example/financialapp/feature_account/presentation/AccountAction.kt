package com.example.financialapp.feature_account.presentation

sealed class AccountAction {
    data class ShowSnackBar(val message: String) : AccountAction()
}