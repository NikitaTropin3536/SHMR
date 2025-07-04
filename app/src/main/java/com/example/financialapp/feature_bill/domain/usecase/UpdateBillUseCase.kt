package com.example.financialapp.feature_bill.domain.usecase

import com.example.financialapp.core.network.retryRequest
import com.example.financialapp.feature_bill.data.model.UpdateAccountDto
import com.example.financialapp.feature_bill.domain.model.AccountBriefModel
import com.example.financialapp.feature_bill.domain.repository.BillRepository

/**
 * Use Case обновления счетов
 * */

class UpdateBillUseCase(
    private val apiRepository: BillRepository
) {

    suspend operator fun invoke(
        id: Int,
        newBill: UpdateAccountDto
    ): Result<AccountBriefModel> {

        return retryRequest {
            val account = apiRepository.updateBill(
                id = id,
                newBill = newBill
            )

            account
        }
    }

}
