package com.example.bill.presentation.current.viewmodel

import com.example.common.core.model.account.AccountBriefModel
import com.example.common.core.model.account.CurrencyOption
import com.example.common.core.model.transaction.TransactionModel
import com.example.core.network.FinResult

/**
 * Состояние экрана счетов
 * */

data class BillState (
    val accounts : List<AccountBriefModel> = emptyList(),
    val transactions : List<TransactionModel> = emptyList(),
    val status: FinResult = FinResult.Loading,
    val chosenCurrency: CurrencyOption? = null,
    val lastSync: Long? = null,
)
