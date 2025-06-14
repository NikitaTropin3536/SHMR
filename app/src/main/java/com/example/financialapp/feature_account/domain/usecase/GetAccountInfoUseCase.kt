package com.example.financialapp.feature_account.domain.usecase

import com.example.financialapp.feature_account.data.repository.GetAccountInfoRepositoryImpl
import com.example.financialapp.feature_account.domain.model.AccountBriefModel

class GetAccountInfoUseCase {
    private val repository = GetAccountInfoRepositoryImpl()

    suspend fun invoke() : List<AccountBriefModel> {
        return repository.getAccountInfo()
    }
}