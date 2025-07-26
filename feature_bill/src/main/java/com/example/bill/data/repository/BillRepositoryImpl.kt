package com.example.bill.data.repository

import com.example.bill.data.model.UpdateAccountDto
import com.example.bill.domain.repository.BillRepository
import com.example.common.core.model.account.AccountBriefModel
import com.example.core.error.ApiException
import com.example.core.network.ktorClient
import com.example.core.network.safeCall
import io.ktor.client.call.body
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import jakarta.inject.Inject

/**
 * Репозиторий для обновления счетов
 * */

class BillRepositoryImpl @Inject constructor() : BillRepository {

    override suspend fun updateBill(
        id: Int,
        newBill: UpdateAccountDto
    ): Result<AccountBriefModel> {
        return safeCall {

            val response: HttpResponse = ktorClient.put("accounts/$id") {
                setBody(newBill)
            }

            if (response.status != HttpStatusCode.OK) {
                throw ApiException("Ошибка API: ${response.status}")
            }

            return@safeCall response.body()

        }
    }

}
