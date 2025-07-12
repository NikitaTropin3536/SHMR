package com.example.bill.domain.usecase

import com.example.bill.domain.repository.BillRepository
import com.example.common.core.model.AccountBriefModel
import com.example.core.network.retryRequest
import jakarta.inject.Inject

/**
 * Use Case получения счетов
 * */

class GetBillInfoUseCase @Inject constructor(
    val billRepository: BillRepository
) {
    suspend operator fun invoke(): Result<List<AccountBriefModel>> {

        return retryRequest {
            val accounts = billRepository.getBillInfo()

            accounts
        }

    }
}
