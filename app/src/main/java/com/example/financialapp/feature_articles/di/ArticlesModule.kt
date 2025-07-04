package com.example.financialapp.feature_articles.di

import com.example.financialapp.feature_articles.data.repository.ArticleRepositoryImpl
import com.example.financialapp.feature_articles.domain.usecase.GetArticlesUseCase
import com.example.financialapp.feature_articles.presentation.viewmodel.ArticlesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val articleModule = module {

    // Репозиторий
    single { ArticleRepositoryImpl() }

    // Use-case
    factory { GetArticlesUseCase(get()) }

    // ViewModel
    viewModel { ArticlesViewModel(get()) }

}