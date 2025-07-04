package com.example.financialapp.feature_transactions.domain.usecase

import com.example.financialapp.core.network.retryRequest
import com.example.financialapp.feature_bill.domain.model.AccountBriefModel
import com.example.financialapp.feature_transactions.data.db.AccountRepositoryImpl
import com.example.financialapp.feature_transactions.domain.repository.TransactionsRepository

/**
 * Use Case для получения счетов пользователя
 * */

class GetAccountUseCase (
    private val localRepository: AccountRepositoryImpl,
    private val apiRepository: TransactionsRepository
) {

    suspend operator fun invoke(): Result<List<AccountBriefModel>> {

        /**
         * нельзя использоваться, так как счет в таком случае будет всегда браться из кеша
         * */

//        localRepository.getAccounts()?.let {
//            return Result.success(it)
//        }

        return retryRequest {
            val accounts = apiRepository.getAccounts()

            accounts.onSuccess {
                localRepository.saveAccounts(it)
            }

            accounts
        }
    }
}
