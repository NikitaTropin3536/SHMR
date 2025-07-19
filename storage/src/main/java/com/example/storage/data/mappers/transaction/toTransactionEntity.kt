package com.example.storage.data.mappers.transaction

import com.example.common.core.model.TransactionModel
import com.example.storage.data.model.TransactionEntity

fun TransactionModel.toTransactionEntity(
    isSynced : Boolean
): TransactionEntity {
    return TransactionEntity(
        id = id,
        accountId = account.id,
        categoryId = categoryModel.id,
        amount = amount,
        transactionDate = transactionDate,
        comment = comment,
        createdAt = createdAt,
        updatedAt = updatedAt,
        isSynced = isSynced
    )
}
