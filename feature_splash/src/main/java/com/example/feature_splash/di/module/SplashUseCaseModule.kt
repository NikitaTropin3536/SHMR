package com.example.feature_splash.di.module

import dagger.Module
import dagger.Provides
import com.example.account.domain.AccountRepository
import com.example.articles.domain.ArticlesRepository
import com.example.feature_splash.domain.GetAccountsUseCase
import com.example.feature_splash.domain.GetArticlesUseCase

/**
 * Модуль use-case сплеш
 * */

@Module
class SplashUseCaseModule {

    @Provides
    fun provideGetAccountsUseCase (
        apiRepository: AccountRepository
    ): GetAccountsUseCase {
        return GetAccountsUseCase(apiRepository)
    }

    @Provides
    fun provideGetArticlesUseCase (
        repository: ArticlesRepository
    ): GetArticlesUseCase {
        return GetArticlesUseCase(repository)
    }

}
