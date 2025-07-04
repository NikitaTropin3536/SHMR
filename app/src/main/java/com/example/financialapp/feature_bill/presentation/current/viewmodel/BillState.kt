package com.example.financialapp.feature_bill.presentation.current.viewmodel

import com.example.financialapp.core.network.FinResult
import com.example.financialapp.feature_bill.domain.model.AccountBriefModel
import com.example.financialapp.feature_bill.domain.model.CurrencyOption

/**
 * Состояние экрана счетов
 * */

data class BillState (
    val accounts : List<AccountBriefModel> = emptyList(),
    val status: FinResult = FinResult.Loading,
    val chosenCurrency: CurrencyOption? = null,
)