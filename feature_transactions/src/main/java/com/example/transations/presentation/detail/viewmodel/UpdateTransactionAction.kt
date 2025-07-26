package com.example.transations.presentation.detail.viewmodel

sealed class UpdateTransactionAction {
    data class ShowSnackBar(val message: String) : UpdateTransactionAction()
    data object OnOpenScreen : UpdateTransactionAction()
}
