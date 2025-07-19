package com.example.storage.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.storage.data.dao.AccountDao
import com.example.storage.data.dao.CategoryDao
import com.example.storage.data.dao.TransactionDao
import com.example.storage.data.model.AccountEntity
import com.example.storage.data.model.CategoryEntity
import com.example.storage.data.model.TransactionEntity

@Database(
    entities = [
        CategoryEntity::class,
        AccountEntity::class,
        TransactionEntity::class,
    ],
    version = 7,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    abstract fun accountDao(): AccountDao

    abstract fun transactionDao(): TransactionDao

}