package com.example.feature_splash.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.example.core.di.utils.ViewModelKey
import com.example.feature_splash.presentation.viewmodel.SplashViewModel

/**
 * Модуль VM сплеш
 * */

@Module
interface SplashViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

}
