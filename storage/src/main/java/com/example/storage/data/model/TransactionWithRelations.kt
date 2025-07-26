package com.example.storage.data.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.storage.data.model.AccountEntity
import com.example.storage.data.model.CategoryEntity
import com.example.storage.data.model.TransactionEntity

/**
 * Модель транзакции, сразу предоставляющая модель категории и аккаунта
 */


data class TransactionWithRelations (
    @Embedded val transaction: TransactionEntity,

    @Relation(
        parentColumn = "accountId",
        entityColumn = "id"
    )
    val accountModel: AccountEntity,

    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val categoryModel: CategoryEntity

)
