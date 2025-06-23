package com.example.financialapp.feature_account.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AccountBriefModel(
    val id: Int,
    val name: String,
    val balance: String,
    val currency: String,
)
