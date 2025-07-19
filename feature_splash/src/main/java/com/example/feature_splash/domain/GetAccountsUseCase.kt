package com.example.feature_splash.domain

import com.example.account.domain.AccountRepository
import com.example.common.core.model.AccountBriefModel

/**
 * Use Case для получения счетов пользователя
 */

class GetAccountsUseCase (
    val apiRepository: AccountRepository
) {

    suspend operator fun invoke(): Result<List<AccountBriefModel>> {
        return apiRepository.getRemoteAccounts()
    }
}