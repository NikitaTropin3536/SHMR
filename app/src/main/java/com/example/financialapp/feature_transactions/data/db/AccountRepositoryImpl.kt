package com.example.financialapp.feature_transactions.data.db

import android.content.Context
import kotlinx.serialization.json.Json
import androidx.core.content.edit
import com.example.financialapp.feature_account.domain.model.AccountBriefModel

class AccountRepositoryImpl(
    context: Context
) {
    companion object {
        private const val PREF_ACCOUNTS_KEY = "cached_account"
    }

    private val preferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    private val json = Json { ignoreUnknownKeys = true }

    fun getAccounts(): List<AccountBriefModel>? {
        val cachedJson = preferences.getString(PREF_ACCOUNTS_KEY, null) ?: return null

        return try {
            json.decodeFromString(cachedJson)
        } catch (e: Exception) {
            null
        }
    }

    fun saveAccounts(
        accounts: List<AccountBriefModel>
    ) {
        val encoded = json.encodeToString(accounts)

        preferences.edit {
            putString(PREF_ACCOUNTS_KEY, encoded)
        }
    }

    fun clearCache() {
        preferences.edit { remove(PREF_ACCOUNTS_KEY) }
    }

}
