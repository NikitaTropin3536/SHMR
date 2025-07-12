package com.example.articles.repository

import com.example.articles.domain.repository.ArticlesRepository
import com.example.common.core.model.CategoryModel
import com.example.core.error.ApiException
import com.example.core.network.ktorClient
import com.example.core.network.safeCall
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

/**
 * Репозиторий для получения статей
 * */

class ArticlesRepositoryImpl : ArticlesRepository {

    override suspend fun getArticles(): Result<List<CategoryModel>> {

        return safeCall {
            val response: HttpResponse = ktorClient.get("categories")

            if (response.status != HttpStatusCode.OK) {
                throw ApiException("Ошибка API: ${response.status}")
            }

            response.body()
        }
    }

}
