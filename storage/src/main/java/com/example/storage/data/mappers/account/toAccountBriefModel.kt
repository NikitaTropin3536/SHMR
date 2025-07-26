package com.example.storage.data.mappers.account

import com.example.common.core.model.account.AccountBriefModel
import com.example.storage.data.model.AccountEntity

fun AccountEntity.toAccountBriefModel(): AccountBriefModel
        = AccountBriefModel(
            id = id,
            name = name,
            balance = balance,
            currency = currency
        )