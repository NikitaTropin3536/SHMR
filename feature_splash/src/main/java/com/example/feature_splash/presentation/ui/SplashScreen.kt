package com.example.feature_splash.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.common.navigation.Route
import com.example.common.ui.item.FinancilityErrorMessage
import com.example.core.network.FinancilityResult
import com.example.feature_splash.presentation.ui.SplashView
import com.example.feature_splash.presentation.viewmodel.SplashEvent
import com.example.feature_splash.presentation.viewmodel.SplashViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen (
    navController: NavController,
    viewModel : SplashViewModel
) {

    val status by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onEvent(SplashEvent.OnLoadData)
    }

    when (status) {
        FinancilityResult.Error -> {
            FinancilityErrorMessage(
                text = "Не удалось загрузить данные",
                onUpdate = {
                    viewModel.onEvent(SplashEvent.OnLoadData)
                },
                modifier = Modifier
            )
        }
        FinancilityResult.Loading -> {
            SplashView()
        }
        FinancilityResult.Success -> {
            SplashView()

            LaunchedEffect(Unit) {
                delay(1000)

                navController.navigate(Route.Expense) {
                    popUpTo(Route.Splash) {
                        inclusive = true
                    }
                }
            }
        }
    }

}
