package com.example.storage.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import com.example.storage.data.encrypted.EncryptedStorage
import com.example.storage.data.sync.AppSyncStorage

@Module
class ArticlesStorageModule {

    @Provides
    fun provideSyncStorage(context: Context): AppSyncStorage {
        return AppSyncStorage(context)
    }

    @Provides
    fun provideEncryptedStorage(context: Context): EncryptedStorage {
        return EncryptedStorage(context)
    }

}
