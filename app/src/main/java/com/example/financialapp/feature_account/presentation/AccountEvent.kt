package com.example.financialapp.feature_account.presentation

sealed class AccountEvent {
    data object OnLoadAccount : AccountEvent()
}