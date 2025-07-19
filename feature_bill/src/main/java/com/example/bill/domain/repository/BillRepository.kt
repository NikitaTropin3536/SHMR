package com.example.bill.domain.repository

import com.example.bill.data.model.UpdateAccountDto
import com.example.common.core.model.AccountBriefModel

interface BillRepository {

    suspend fun updateBill(
        id: Int,
        newBill : UpdateAccountDto
    ): Result<AccountBriefModel>

}
