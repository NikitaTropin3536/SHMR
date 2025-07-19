package com.example.articles.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import com.example.articles.di.modules.ArticlesRepositoryModule
import com.example.articles.di.modules.ArticlesUseCaseModule
import com.example.articles.di.modules.ArticlesViewModelModule
import com.example.core.di.CoreComponent
import com.example.core.di.modules.SharedViewModelModule
import com.example.storage.di.DatabaseComponent

/**
 * Компонент, который хранит в себе VM, репозитории и use-case фичи статей
 */

@ArticlesScope
@Component(
    dependencies = [
        CoreComponent::class,
        DatabaseComponent::class,
    ],
    modules = [
        ArticlesRepositoryModule::class,
        ArticlesUseCaseModule::class,
        ArticlesViewModelModule::class,
        SharedViewModelModule::class
    ]
)
interface ArticlesComponent {

    fun viewModelFactory(): ViewModelProvider.Factory

    @Component.Factory
    interface Factory {
        fun create(
            core: CoreComponent,
            db: DatabaseComponent
        ): ArticlesComponent
    }

}
