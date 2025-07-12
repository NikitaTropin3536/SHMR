package com.example.core.di.modules

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import com.example.core.di.utils.ViewModelFactory

/**
 * Общий модуль фабрики VM
 * */

@Module
interface SharedViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
