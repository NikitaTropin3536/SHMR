package com.example.transactions.data.mappers

import com.example.storage.data.model.TransactionEntity
import com.example.transactions.data.dto.RequestTransactionDto

fun TransactionEntity.toTransactionDto(): RequestTransactionDto {

    return RequestTransactionDto(
        accountId = accountId,
        categoryId = categoryId,
        amount = amount,
        transactionDate = transactionDate,
        comment = comment,
    )
}