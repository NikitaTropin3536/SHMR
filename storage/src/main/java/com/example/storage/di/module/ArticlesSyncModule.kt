package com.example.storage.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import com.example.storage.data.sync.AppSyncStorage

@Module
class ArticlesSyncModule {

    @Provides
    fun provideSyncStorage(context: Context): AppSyncStorage {
        return AppSyncStorage(context)
    }

}
