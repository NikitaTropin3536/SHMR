package com.example.feature_splash.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.common.navigation.Route
import com.example.common.ui.item.FinErrorMessage
import com.example.common.ui.item.FinSnackBar
import com.example.core.network.FinResult
import com.example.feature_splash.presentation.viewmodel.SplashAction
import com.example.feature_splash.presentation.viewmodel.SplashEvent
import com.example.feature_splash.presentation.viewmodel.SplashViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreen (
    navController: NavController,
    viewModel : SplashViewModel
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.onEvent(SplashEvent.OnLoadData)

        viewModel.action.collectLatest { action ->
            when (action) {
                SplashAction.OnOpenMainScreen -> {
                    navController.navigate(Route.Expense) {
                        popUpTo(Route.Splash) {
                            inclusive = true
                        }
                    }
                }
                is SplashAction.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(action.message)
                }
            }
        }
    }

    Scaffold (
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.onSurface,
        snackbarHost = { FinSnackBar(snackBarHostState) }
    ) { padding ->

        when (state.status) {
            FinResult.Error -> {
                FinErrorMessage(
                    text = "Не удалось загрузить данные",
                    onUpdate = {
                        viewModel.onEvent(SplashEvent.OnLoadData)
                    },
                    modifier = Modifier
                )
            }
            FinResult.Loading -> {
                SplashView(
                    modifier = Modifier
                        .padding(padding)
                )
            }
            FinResult.Success -> {
                if (state.isPin) {
                    PinView(
                        modifier = Modifier
                            .padding(padding),
                        state = state,
                        onEvent = {
                            viewModel.onEvent(it)
                        }
                    )
                } else {
                    LaunchedEffect(Unit) {
                        navController.navigate(Route.Expense) {
                            popUpTo(Route.Splash) {
                                inclusive = true
                            }
                        }
                    }
                }
            }
        }
    }
}
