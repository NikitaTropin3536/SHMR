package com.example.bill.presentation.current.viewmodel

import com.example.common.core.model.AccountBriefModel
import com.example.common.core.model.CurrencyOption
import com.example.core.network.FinancilityResult

/**
 * Состояние экрана счетов
 * */

data class BillState (
    val accounts : List<AccountBriefModel> = emptyList(),
    val status: FinancilityResult = FinancilityResult.Loading,
    val chosenCurrency: CurrencyOption? = null,
    val lastSync: Long? = null,
)
