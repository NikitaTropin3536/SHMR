package com.example.transations.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import com.example.core.di.CoreComponent
import com.example.core.di.modules.SharedViewModelModule
import com.example.transations.di.module.TransactionsRepositoryModule
import com.example.transations.di.module.TransactionsUseCaseModule
import com.example.transations.di.module.TransactionsViewModelModule

/**
 * Компонент, который хранит в себе VM, репозитории и use-case фичи транзакций
 */

@TransactionScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [
        TransactionsRepositoryModule::class,
        TransactionsUseCaseModule::class,
        TransactionsViewModelModule::class,
        SharedViewModelModule::class
    ]
)
interface TransactionComponent {

    fun viewModelFactory(): ViewModelProvider.Factory

    @Component.Factory
    interface Factory {
        fun create(core: CoreComponent): TransactionComponent
    }

}

