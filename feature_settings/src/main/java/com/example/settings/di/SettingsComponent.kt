package com.example.settings.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import com.example.core.di.CoreComponent
import com.example.core.di.modules.SharedViewModelModule
import com.example.settings.di.modules.SettingsViewModelModule
import com.example.storage.di.DatabaseComponent


@SettingsScope
@Component(
    dependencies = [
        CoreComponent::class,
        DatabaseComponent::class,
    ],
    modules = [
        SettingsViewModelModule::class,
        SharedViewModelModule::class
    ]
)
interface SettingsComponent {

    fun viewModelFactory(): ViewModelProvider.Factory

    @Component.Factory
    interface Factory {
        fun create(
            core: CoreComponent,
            db: DatabaseComponent
        ): SettingsComponent
    }

}
