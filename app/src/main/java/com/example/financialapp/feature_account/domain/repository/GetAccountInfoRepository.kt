package com.example.financialapp.feature_account.domain.repository

import com.example.financialapp.feature_account.domain.model.AccountBriefModel

interface GetAccountInfoRepository {

    suspend fun getAccountInfo(): Result<List<AccountBriefModel>>

}
