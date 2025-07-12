package com.example.transations.domain.repository

import com.example.common.core.model.AccountBriefModel

interface AccountRepository {

    fun getAccounts(): List<AccountBriefModel>?

    fun saveAccounts(
        accounts: List<AccountBriefModel>
    )

    fun clearCache()

}
