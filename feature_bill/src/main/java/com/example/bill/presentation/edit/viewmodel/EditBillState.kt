package com.example.bill.presentation.edit.viewmodel

import com.example.common.core.model.AccountBriefModel
import com.example.common.core.model.CurrencyOption
import com.example.core.network.FinResult

/**
 * Состояние экрана счетов
 * */


data class EditBillState (
    val accounts : List<AccountBriefModel> = emptyList(),
    val status: FinResult = FinResult.Loading,
    val enteredName: String = "",
    val enteredAmount: String = "",
    val chosenCurrency: CurrencyOption = CurrencyOption("NONE", "-", "NONE"),
)
