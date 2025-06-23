package com.example.financialapp.feature_account.domain.usecase

import com.example.financialapp.core.network.retryRequest
import com.example.financialapp.feature_account.domain.model.AccountBriefModel
import com.example.financialapp.feature_account.domain.repository.GetAccountInfoRepository

class GetAccountInfoUseCase(
    private val apiRepository: GetAccountInfoRepository
) {
    suspend operator fun invoke(): Result<List<AccountBriefModel>> {

        return retryRequest {
            val accounts = apiRepository.getAccountInfo()

            accounts
        }

    }
}