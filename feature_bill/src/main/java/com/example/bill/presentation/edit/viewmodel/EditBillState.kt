package com.example.bill.presentation.edit.viewmodel

import com.example.common.core.model.AccountBriefModel
import com.example.common.core.model.CurrencyOption
import com.example.core.network.FinancilityResult

/**
 * Состояние экрана счетов
 * */


data class EditBillState (
    val accounts : List<AccountBriefModel> = emptyList(),
    val status: FinancilityResult = FinancilityResult.Loading,
    val enteredName: String = "",
    val enteredAmount: String = "",
    val chosenCurrency: CurrencyOption = CurrencyOption("NONE", "-", "NONE"),
    val lastSync: Long? = null,
)
