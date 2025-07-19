package com.example.storage.data.mappers.account

import com.example.common.core.model.AccountBriefModel
import com.example.storage.data.model.AccountEntity

fun AccountBriefModel.toAccountEntity(): AccountEntity
        = AccountEntity(
            id = id,
            name = name,
            balance = balance,
            currency = currency
        )
