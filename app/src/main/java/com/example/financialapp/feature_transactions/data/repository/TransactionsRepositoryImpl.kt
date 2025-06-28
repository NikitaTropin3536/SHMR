package com.example.financialapp.feature_transactions.data.repository

import com.example.financialapp.BuildConfig
import com.example.financialapp.core.error.ApiException
import com.example.financialapp.core.network.ktorClient
import com.example.financialapp.core.network.safeCall
import com.example.financialapp.feature_bill.domain.model.AccountBriefModel
import com.example.financialapp.feature_transactions.data.dto.TransactionDto
import com.example.financialapp.feature_transactions.domain.model.TransactionModel
import com.example.financialapp.feature_transactions.domain.repository.TransactionsRepository
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

/**
 * Репозиторий, который выолняет функции
 * - получения транзакций
 * - получения счетов
 * - добавления транзакции
 * */

class TransactionsRepositoryImpl : TransactionsRepository {

    override suspend fun getTransactions(
        accountId: Int,
        startDate: String,
        endDate: String,
    ): Result<List<TransactionModel>> {
        return safeCall {
            val response: HttpResponse =
                ktorClient.get("${BuildConfig.BASE_URL}/transactions/account/${accountId}/period") {
                    url {
                        parameters.append("startDate", startDate)
                        parameters.append("endDate", endDate)
                    }
                }

            if (response.status != HttpStatusCode.OK) {
                throw ApiException("Ошибка API: ${response.status}")
            }

            response.body()
        }
    }

    override suspend fun getAccounts(): Result<List<AccountBriefModel>> {
        return safeCall {
            val response: HttpResponse = ktorClient.get("${BuildConfig.BASE_URL}/accounts")

            if (response.status != HttpStatusCode.OK) {
                throw ApiException("Ошибка API: ${response.status}")
            }

            response.body()
        }
    }

    override suspend fun createExpense(transaction: TransactionDto): Result<Unit> {
        return safeCall {
            val response: HttpResponse = ktorClient.post("${BuildConfig.BASE_URL}/transactions") {
                setBody(transaction)
            }

            if (response.status != HttpStatusCode.OK && response.status != HttpStatusCode.Created) {
                throw ApiException("Ошибка API: ${response.status}")
            }

            response.body()
        }
    }
}
