package com.example.storage.di

import dagger.Component
import com.example.core.di.CoreComponent
import com.example.storage.data.dao.AccountDao
import com.example.storage.data.dao.CategoryDao
import com.example.storage.data.dao.TransactionDao
import com.example.storage.di.module.AppRoomModule
import com.example.storage.di.module.ArticlesSyncModule

/**
 * Компонент, который хранит в себе базу данных для офлайн-режима
 */

@DatabaseScope
@Component(
    dependencies = [
        CoreComponent::class
    ],
    modules = [
        AppRoomModule::class,
        ArticlesSyncModule::class
    ]
)
interface DatabaseComponent {

    fun categoryDao(): CategoryDao

    fun accountDao(): AccountDao

    fun transactionDao(): TransactionDao

    @Component.Factory
    interface Factory {
        fun create(
            coreComponent: CoreComponent
        ): DatabaseComponent
    }

}