package com.example.transations.domain.usecase

import com.example.account.domain.AccountRepository
import com.example.common.core.model.account.AccountBriefModel
import com.example.core.network.retryRequest

/**
 * Use Case для получения счетов пользователя
 */

class GetAccountsUseCase (
    val apiRepository: AccountRepository
) {

    suspend operator fun invoke(): Result<List<AccountBriefModel>> {

        return retryRequest {
            return@retryRequest apiRepository.getCashedAccounts()
        }

    }
}
