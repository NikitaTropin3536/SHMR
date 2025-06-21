package com.example.financialapp.feature_transactions.domain.usecase

import com.example.financialapp.core.network.retryRequest
import com.example.financialapp.feature_account.domain.model.AccountBriefModel
import com.example.financialapp.feature_transactions.data.db.AccountRepositoryImpl
import com.example.financialapp.feature_transactions.domain.repository.TransactionsRepository

class GetAccountUseCase (
    private val localRepository: AccountRepositoryImpl,
    private val apiRepository: TransactionsRepository
) {

    suspend operator fun invoke(): Result<List<AccountBriefModel>> {

        localRepository.getAccounts()?.let {
            println("FAPP load acc $it")
            return Result.success(it)
        }

        println("FAPP go to internet")

        return retryRequest {
            val accounts = apiRepository.getAccounts()

            accounts.onSuccess {
                localRepository.saveAccounts(it)
            }

            accounts
        }
    }
}
