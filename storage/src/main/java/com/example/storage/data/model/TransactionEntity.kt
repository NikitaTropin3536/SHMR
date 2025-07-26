package com.example.storage.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.storage.data.model.AccountEntity
import com.example.storage.data.model.CategoryEntity

@Entity(
    tableName = "transactions",
    foreignKeys = [
        ForeignKey(entity = AccountEntity::class, parentColumns = ["id"], childColumns = ["accountId"]),
        ForeignKey(entity = CategoryEntity::class, parentColumns = ["id"], childColumns = ["categoryId"])
    ],
    indices = [Index("accountId"), Index("categoryId")]
)
data class TransactionEntity (
    @PrimaryKey val id: Int,

    val accountId: Int,
    val categoryId: Int,

    val amount: String,
    val transactionDate: String,
    val comment: String?,
    val createdAt: String,
    val updatedAt: String,
    val isSynced: Boolean
)
