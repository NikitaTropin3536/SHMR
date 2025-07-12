package com.example.articles.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.example.articles.presentation.viewmodel.ArticlesViewModel
import com.example.core.di.utils.ViewModelKey

/**
 * Модуль VM статей
 * */

@Module
interface ArticlesViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ArticlesViewModel::class)
    fun bindArticlesViewModel(viewModel: ArticlesViewModel): ViewModel

}
