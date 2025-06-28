package com.example.financialapp.navigation

import androidx.annotation.DrawableRes
import com.example.financialapp.R

/**
Все табы приложения
 */

sealed class Bar (
    val title: String,
    @DrawableRes val icon: Int,
    val route: Route,
) {

    data object Expenses : Bar(
        title = "Расходы",
        icon = R.drawable.ic_expenses,
        route = Route.Expenses
    )

    data object Income : Bar(
        title = "Доходы",
        icon = R.drawable.ic_income,
        route = Route.Income,
    )

    data object Account : Bar(
        title = "Счёт",
        icon = R.drawable.ic_account,
        route = Route.Account,
    )

    data object Articles : Bar(
        title = "Статьи",
        icon = R.drawable.ic_articles,
        route = Route.Articles,
    )

    data object Settings : Bar(
        title = "Настройки",
        icon = R.drawable.ic_settings,
        route = Route.Settings,
    )

    companion object {
        val items = listOf(Expenses, Income,
            Account, Articles, Settings)
    }

}