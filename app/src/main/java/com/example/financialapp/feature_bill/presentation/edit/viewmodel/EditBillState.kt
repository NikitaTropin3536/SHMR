package com.example.financialapp.feature_bill.presentation.edit.viewmodel

import com.example.financialapp.core.network.FinResult
import com.example.financialapp.feature_bill.domain.model.AccountBriefModel
import com.example.financialapp.feature_bill.domain.model.CurrencyOption

/**
 * Состояние экрана счетов
 * */

data class EditBillState(
    val accounts: List<AccountBriefModel> = emptyList(),
    val status: FinResult = FinResult.Loading,
    val enteredName: String = "",
    val enteredAmount: String = "",
    val chosenCurrency: CurrencyOption = CurrencyOption("NONE", "-", "NONE"),
)
