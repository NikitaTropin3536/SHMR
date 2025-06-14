package com.example.financialapp.feature_account.presentation

import com.example.financialapp.feature_account.domain.model.AccountBriefModel

data class AccountState (
    val account : List<AccountBriefModel> = emptyList()
)