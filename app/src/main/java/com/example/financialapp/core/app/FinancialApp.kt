package com.example.financialapp.core.app

import com.example.financialapp.navigation.Route
import com.example.financialapp.ui.theme.FinancialAppTheme
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.financialapp.feature_bill.presentation.ui.AccountScreen
import com.example.financialapp.feature_articles.presentation.ui.ArticlesScreen
import com.example.financialapp.feature_settings.SettingsScreen
import com.example.financialapp.feature_transactions.presentation.expenses.create.ui.CreateExpensesScreen
import com.example.financialapp.feature_transactions.presentation.expenses.history.ui.HistoryExpensesScreen
import com.example.financialapp.feature_transactions.presentation.expenses.today.ui.ExpensesScreen
import com.example.financialapp.feature_transactions.presentation.income.history.ui.HistoryIncomeScreen
import com.example.financialapp.feature_transactions.presentation.income.today.ui.IncomeScreen
import com.example.financialapp.splash.SplashScreen

@Composable
fun FinancialApp() {
    FinancialAppTheme {

        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Route.Splash,
        ) {
            composable<Route.Splash>(
                exitTransition = { slideOutHorizontally() },
                popEnterTransition = { slideInHorizontally() }
            ) {
                SplashScreen(
                    navController = navController
                )
            }

            navigation<Route.Expenses>(
                startDestination = Route.TodayExpenses
            ) {
                composable<Route.TodayExpenses> {
                    ExpensesScreen(
                        navController = navController
                    )
                }

                composable<Route.HistoryExpenses> {
                    HistoryExpensesScreen(
                        navController = navController
                    )
                }

                composable<Route.CreateExpenses> {
                    CreateExpensesScreen(
                        navController = navController
                    )
                }
            }

            navigation<Route.Income>(
                startDestination = Route.TodayIncome
            ) {
                composable<Route.TodayIncome> {
                    IncomeScreen(
                        navController = navController
                    )
                }

                composable<Route.HistoryIncomes> {
                    HistoryIncomeScreen(
                        navController = navController
                    )
                }
            }

            navigation<Route.Account>(
                startDestination = Route.CurrentAccount
            ) {
                composable<Route.CurrentAccount> {
                    AccountScreen(
                        navController = navController
                    )
                }
            }

            navigation<Route.Articles>(
                startDestination = Route.MyArticles
            ) {
                composable<Route.MyArticles> {
                    ArticlesScreen(
                        navController = navController
                    )
                }
            }

            navigation<Route.Settings>(
                startDestination = Route.AllSettings
            ) {
                composable<Route.AllSettings> {
                    SettingsScreen(
                        navController = navController
                    )
                }
            }
        }
    }
}