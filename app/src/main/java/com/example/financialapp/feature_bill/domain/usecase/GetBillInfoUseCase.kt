package com.example.financialapp.feature_bill.domain.usecase

import com.example.financialapp.core.network.retryRequest
import com.example.financialapp.feature_bill.domain.model.AccountBriefModel
import com.example.financialapp.feature_bill.domain.repository.BillRepository

/**
 * Use Case получения счетов
 * */

class GetBillInfoUseCase(
    private val apiRepository: BillRepository
) {
    suspend operator fun invoke(): Result<List<AccountBriefModel>> {

        return retryRequest {
            val accounts = apiRepository.getBillInfo()

            accounts
        }

    }
}