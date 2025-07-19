package com.example.transations.data.mappers

import com.example.common.core.converter.convertMillisToDate
import com.example.storage.data.model.TransactionEntity
import com.example.transations.data.dto.ResponseTransactionDto

fun ResponseTransactionDto.ResponsetoTransactionEntity(
    isSynced : Boolean
): TransactionEntity {
    val timeStamp = System.currentTimeMillis()

    return TransactionEntity(
        id = timeStamp.toInt(),
        accountId = accountId,
        categoryId = categoryId,
        amount = amount,
        transactionDate = transactionDate,
        comment = comment,
        createdAt = convertMillisToDate(timeStamp),
        updatedAt = convertMillisToDate(timeStamp),
        isSynced = isSynced
    )
}