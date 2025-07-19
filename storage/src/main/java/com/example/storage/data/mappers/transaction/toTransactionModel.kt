package com.example.storage.data.mappers.transaction

import com.example.common.core.model.TransactionModel
import com.example.storage.data.mappers.account.toAccountBriefModel
import com.example.storage.data.mappers.category.toCategoryModel
import com.example.storage.data.model.TransactionWithRelations

fun TransactionWithRelations.toTransactionModel(): TransactionModel {
    return TransactionModel(
        id = transaction.id,
        account = accountModel.toAccountBriefModel(),
        categoryModel = categoryModel.toCategoryModel(),
        amount = transaction.amount,
        transactionDate = transaction.transactionDate,
        comment = transaction.comment,
        createdAt = transaction.createdAt,
        updatedAt = transaction.updatedAt,
    )
}
