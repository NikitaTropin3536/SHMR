package com.example.financialapp.feature_bill.data.repository

import com.example.financialapp.BuildConfig
import com.example.financialapp.core.error.ApiException
import com.example.financialapp.core.network.ktorClient
import com.example.financialapp.core.network.safeCall
import com.example.financialapp.feature_bill.domain.model.AccountBriefModel
import com.example.financialapp.feature_bill.domain.repository.GetBillInfoRepository
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

/**
 * Репозиторий для получения счетов
 * */

class GetBillInfoRepositoryImpl : GetBillInfoRepository {

    override suspend fun getBillInfo(): Result<List<AccountBriefModel>> {
        return safeCall {
            val response: HttpResponse = ktorClient.get("${BuildConfig.BASE_URL}/accounts")

            if (response.status != HttpStatusCode.OK) {
                throw ApiException("Ошибка API: ${response.status}")
            }

            response.body()
        }
    }
}
