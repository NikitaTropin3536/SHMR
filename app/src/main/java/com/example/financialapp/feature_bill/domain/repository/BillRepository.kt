package com.example.financialapp.feature_bill.domain.repository

import com.example.financialapp.feature_bill.data.model.UpdateAccountDto
import com.example.financialapp.feature_bill.domain.model.AccountBriefModel

interface BillRepository {

    suspend fun getBillInfo(): Result<List<AccountBriefModel>>

    suspend fun updateBill(
        id: Int,
        newBill : UpdateAccountDto
    ): Result<AccountBriefModel>

}
