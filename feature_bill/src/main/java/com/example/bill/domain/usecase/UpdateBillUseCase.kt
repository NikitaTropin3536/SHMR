package com.example.bill.domain.usecase

import com.example.bill.data.model.UpdateAccountDto
import com.example.bill.domain.repository.BillRepository
import com.example.common.core.model.AccountBriefModel
import com.example.core.network.retryRequest
import jakarta.inject.Inject

/**
 * Use Case обновления счетов
 * */

class UpdateBillUseCase @Inject constructor(
    val apiRepository: BillRepository
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
