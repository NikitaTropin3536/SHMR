package com.example.settings.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.example.core.di.utils.ViewModelKey
import com.example.settings.presentation.viewmodel.SettingsViewModel

@Module
interface SettingsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    fun bindSettingsViewModel(viewModel: SettingsViewModel): ViewModel

}
