package com.example.bill.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import com.example.bill.di.modules.BillRepositoryModule
import com.example.bill.di.modules.BillUseCaseModule
import com.example.bill.di.modules.BillViewModelModule
import com.example.core.di.CoreComponent
import com.example.core.di.modules.SharedViewModelModule

/**
 * Компонент, который хранит в себе VM, репозитории и use-case фичи счета
 */

@BillScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [
        BillRepositoryModule::class,
        BillUseCaseModule::class,
        BillViewModelModule::class,
        SharedViewModelModule::class
    ]
)
interface BillComponent {

    fun viewModelFactory(): ViewModelProvider.Factory

    @Component.Factory
    interface Factory {
        fun create(core: CoreComponent): BillComponent
    }

}
