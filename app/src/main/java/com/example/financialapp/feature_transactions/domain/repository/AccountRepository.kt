package com.example.financialapp.feature_transactions.domain.repository

import com.example.financialapp.feature_bill.domain.model.AccountBriefModel

interface AccountRepository {

    fun getAccounts(): List<AccountBriefModel>?

    fun saveAccounts(
        accounts: List<AccountBriefModel>
    )

    fun clearCache()

}