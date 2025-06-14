package com.example.financialapp.feature_account.data.repository

import com.example.financialapp.feature_account.domain.model.AccountBriefModel
import com.example.financialapp.feature_account.domain.repository.GetAccountInfoRepository

class GetAccountInfoRepositoryImpl : GetAccountInfoRepository {

    override suspend fun getAccountInfo(): List<AccountBriefModel> {
        /* TODO API */

        return listOf(
            AccountBriefModel(
                id = "...",
                name = "Основной счет",
                balance = "-670 000",
                currency = "₽"
            )
        )
    }

}
