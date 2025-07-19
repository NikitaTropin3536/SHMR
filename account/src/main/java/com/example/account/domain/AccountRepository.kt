package com.example.account.domain

import com.example.common.core.model.AccountBriefModel

interface AccountRepository {

    suspend fun getRemoteAccounts(): Result<List<AccountBriefModel>>

    suspend fun getCashedAccounts(): Result<List<AccountBriefModel>>

}
