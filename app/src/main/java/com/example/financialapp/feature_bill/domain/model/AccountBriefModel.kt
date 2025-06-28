package com.example.financialapp.feature_bill.domain.model

import kotlinx.serialization.Serializable

/**
 * Модель счета
 * */

@Serializable
data class AccountBriefModel(
    val id: Int,
    val name: String,
    val balance: String,
    val currency: String,
)
