package com.example.financialapp

object MainRoutes {
    const val EXPENSES_ROUTE = "expenses"
    const val GAINS_ROUTE = "gains"
    const val ACCOUNT_ROUTE = "account"
    const val ARTICLES_ROUTE = "articles"
    const val SETTINGS_ROUTE = "settings"
}

val navItems = listOf(
    BottomNavItem(
        title = R.string.expenses,
        icon = R.drawable.ic_expenses,
        route = MainRoutes.EXPENSES_ROUTE
    ),
    BottomNavItem(
        title = R.string.gains,
        icon = R.drawable.ic_gains,
        route = MainRoutes.GAINS_ROUTE
    ),
    BottomNavItem(
        title = R.string.account,
        icon = R.drawable.ic_account,
        route = MainRoutes.ACCOUNT_ROUTE
    ),
    BottomNavItem(
        title = R.string.articles,
        icon = R.drawable.ic_articles,
        route = MainRoutes.ARTICLES_ROUTE
    ),
    BottomNavItem(
        title = R.string.settings,
        icon = R.drawable.ic_settings,
        route = MainRoutes.SETTINGS_ROUTE
    )
)