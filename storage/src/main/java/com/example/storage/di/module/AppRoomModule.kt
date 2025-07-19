package com.example.storage.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import com.example.storage.data.dao.AccountDao
import com.example.storage.data.dao.CategoryDao
import com.example.storage.data.dao.TransactionDao
import com.example.storage.data.database.AppDatabase

@Module
class AppRoomModule {

    @Provides
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "financility.db"
        ).build()
    }

    @Provides
    fun provideCategoryDao(db: AppDatabase): CategoryDao = db.categoryDao()

    @Provides
    fun provideAccountDao(db: AppDatabase): AccountDao = db.accountDao()

    @Provides
    fun provideTransactionDao(db: AppDatabase): TransactionDao = db.transactionDao()


}
