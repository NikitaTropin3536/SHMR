package com.example.financialapp.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.example.common.navigation.Route
import kotlinx.coroutines.delay

@Composable
fun SplashScreen (
    navController: NavController
) {
    LaunchedEffect(Unit) {
        delay(2000)

        navController.navigate(Route.Expense) {
            popUpTo(Route.Splash) {
                inclusive = true
            }
        }
    }

    SplashView()
}