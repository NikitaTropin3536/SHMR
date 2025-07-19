package com.example.feature_splash.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import com.example.core.di.CoreComponent
import com.example.core.di.modules.SharedViewModelModule
import com.example.feature_splash.di.module.SplashRepositoryModule
import com.example.feature_splash.di.module.SplashUseCaseModule
import com.example.feature_splash.di.module.SplashViewModelModule
import com.example.storage.di.DatabaseComponent

/**
 * Компонент, который хранит в себе VM фичи сплеш
 */

@SplashScope
@Component(
    dependencies = [
        CoreComponent::class,
        DatabaseComponent::class,
    ],
    modules = [
        SplashRepositoryModule::class,
        SplashUseCaseModule::class,
        SplashViewModelModule::class,
        SharedViewModelModule::class
    ]
)
interface SplashComponent {

    fun viewModelFactory(): ViewModelProvider.Factory

    @Component.Factory
    interface Factory {
        fun create(
            core: CoreComponent,
            db: DatabaseComponent
        ): SplashComponent
    }

}