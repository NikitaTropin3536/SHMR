package com.example.financialapp.feature_transactions.presentation.expenses.history.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.financialapp.R
import com.example.financialapp.components.item.FinLoadingBar
import com.example.financialapp.components.item.FinSnackBar
import com.example.financialapp.components.nav.BottomBar
import com.example.financialapp.components.nav.TopBar
import com.example.financialapp.core.network.FinResult
import com.example.financialapp.feature_transactions.presentation.expenses.history.HistoryExpensesAction
import com.example.financialapp.feature_transactions.presentation.expenses.history.HistoryExpensesEvent
import com.example.financialapp.feature_transactions.presentation.expenses.history.HistoryExpensesViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun HistoryExpensesScreen(
    viewModel: HistoryExpensesViewModel = koinViewModel<HistoryExpensesViewModel>(),
    navController: NavController
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(true) {
        viewModel.onEvent(HistoryExpensesEvent.OnLoadExpenses)

        viewModel.action.collectLatest { event ->
            when (event) {
                is HistoryExpensesAction.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController
            )
        },
        topBar = {
            TopBar(
                title = "Моя история",
                actions = {
                    IconButton(
                        onClick = { /* TODO */ }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_statistics),
                            contentDescription = "Статистика",
                            tint = MaterialTheme.colorScheme.surfaceContainer
                        )
                    }
                },
                navIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_btn_back),
                            contentDescription = "Назад",
                            tint = MaterialTheme.colorScheme.surfaceContainer
                        )
                    }
                }
            )
        },
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.onSurface,
        snackbarHost = { FinSnackBar(snackBarHostState) }
    ) { padding ->

        if (state.status != FinResult.Success) {
            FinLoadingBar(
                modifier = Modifier
                    .padding(padding)
            )
        } else {
            HistoryExpensesView(
                modifier = Modifier.padding(padding),
                state = state
            ) {
                viewModel.onEvent(it)
            }
        }

    }
}
