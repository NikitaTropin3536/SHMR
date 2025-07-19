package com.example.articles.data

import com.example.articles.domain.ArticlesRepository
import com.example.common.constants.Constants
import com.example.common.core.model.CategoryModel
import com.example.core.error.ApiException
import com.example.core.error.OfflineDataException
import com.example.core.network.ktorClient
import com.example.core.network.safeCall
import com.example.storage.data.dao.CategoryDao
import com.example.storage.data.mappers.category.toCategoryEntity
import com.example.storage.data.mappers.category.toCategoryModel
import com.example.storage.data.sync.AppSyncStorage
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import jakarta.inject.Inject

/**
 * Репозиторий для получения статей
 * */

class ArticlesRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao,
    private val appSyncStorage: AppSyncStorage
): ArticlesRepository {

    override suspend fun getArticles(): Result<List<CategoryModel>> {

        return safeCall {
            try {

                val response = ktorClient.get("categories")

                if (response.status != HttpStatusCode.OK) {
                    throw ApiException("Ошибка API: ${response.status}")
                }

                val articles = response.body<List<CategoryModel>>()

                /* save to local DB */
                categoryDao.insertAll(articles.map {
                    it.toCategoryEntity()
                })

                /* save last sync */
                appSyncStorage.saveSyncTime(
                    feature = Constants.ARTICLES_SYNC,
                    timestamp = System.currentTimeMillis()
                )

                return@safeCall articles

            } catch (e: Exception) {

                /* get cashed articles */
                val cached = categoryDao.getAll().map {
                    it.toCategoryModel()
                }

                /* if not cashed data */
                if (cached.isNotEmpty()) {
                    throw OfflineDataException(cached)
                }

                throw e
            }
        }
    }

}