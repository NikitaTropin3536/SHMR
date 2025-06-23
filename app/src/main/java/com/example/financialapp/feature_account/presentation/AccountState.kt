package com.example.financialapp.feature_account.presentation

import com.example.financialapp.core.network.FinResult
import com.example.financialapp.feature_account.domain.model.AccountBriefModel

data class AccountState (
    val accounts : List<AccountBriefModel> = emptyList(),
    val status: FinResult = FinResult.Loading,
)