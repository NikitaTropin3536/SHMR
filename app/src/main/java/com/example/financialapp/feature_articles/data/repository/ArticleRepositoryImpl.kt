package com.example.financialapp.feature_articles.data.repository

import com.example.financialapp.BuildConfig
import com.example.financialapp.core.network.ApiException
import com.example.financialapp.core.network.ktorClient
import com.example.financialapp.core.network.safeCall
import com.example.financialapp.feature_articles.domain.repository.ArticlesRepository
import com.example.financialapp.feature_transactions.domain.model.CategoryModel
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

class ArticleRepositoryImpl : ArticlesRepository {

    override suspend fun getArticles(): Result<List<CategoryModel>> {

        return safeCall {
            val response: HttpResponse = ktorClient.get("${BuildConfig.BASE_URL}/categories")

            if (response.status != HttpStatusCode.OK) {
                throw ApiException("Ошибка API: ${response.status}")
            }

            response.body()
        }
    }

}