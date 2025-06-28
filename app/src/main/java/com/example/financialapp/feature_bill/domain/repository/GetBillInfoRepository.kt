package com.example.financialapp.feature_bill.domain.repository

import com.example.financialapp.feature_bill.domain.model.AccountBriefModel

interface GetBillInfoRepository {

    suspend fun getBillInfo(): Result<List<AccountBriefModel>>

}
