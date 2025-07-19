package com.example.bill.domain.usecase

import com.example.account.domain.AccountRepository
import com.example.common.core.model.AccountBriefModel
import com.example.core.network.retryRequest
import jakarta.inject.Inject

/**
 * Use Case получения счетов
 * */

class GetBillInfoUseCase @Inject constructor(
    val accountRepository: AccountRepository
) {
    suspend operator fun invoke(): Result<List<AccountBriefModel>> {

        return retryRequest {
            val accounts = accountRepository.getRemoteAccounts()

            accounts
        }

    }
}
