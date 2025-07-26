package com.example.transations.presentation.expenses.history.ui

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.common.R
import com.example.common.navigation.Route
import com.example.common.ui.item.FinErrorMessage
import com.example.common.ui.item.FinLoadingBar
import com.example.common.ui.item.FinSnackBar
import com.example.common.ui.nav.FinBottomBar
import com.example.common.ui.nav.FinTopBar
import com.example.core.network.FinResult
import com.example.transations.presentation.expenses.history.viewmodel.HistoryExpensesAction
import com.example.transations.presentation.expenses.history.viewmodel.HistoryExpensesEvent
import com.example.transations.presentation.expenses.history.viewmodel.HistoryExpensesViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun HistoryExpensesScreen (
    viewModel: HistoryExpensesViewModel,
    navController: NavController
) {

    var error: String? by remember {
        mutableStateOf(null)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(true) {
        viewModel.onEvent(HistoryExpensesEvent.OnLoadExpenses)

        viewModel.action.collectLatest { action ->
            when (action) {
                is HistoryExpensesAction.ShowSnackBar -> {
                    error = action.message

                    snackBarHostState.showSnackbar(action.message)
                }
            }
        }
    }

    Scaffold (
        bottomBar = {
            FinBottomBar(
                navController = navController
            )
        },
        topBar = {
            FinTopBar(
                title = stringResource(com.example.transations.R.string.my_history),
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Route.AnalysisExpense)
                        }
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

        when (state.status) {
            FinResult.Error -> {
                FinErrorMessage(
                    modifier = Modifier
                        .padding(padding),
                    text = error,
                    onUpdate = {
                        viewModel.onEvent(HistoryExpensesEvent.OnLoadExpenses)
                    }
                )
            }
            FinResult.Loading -> {
                FinLoadingBar(
                    modifier = Modifier
                        .padding(padding)
                )
            }
            FinResult.Success -> {
                HistoryExpensesView(
                    modifier = Modifier
                        .padding(padding),
                    state = state,
                    onEvent = {
                        viewModel.onEvent(it)
                    }
                ) {
                    val json = Json.encodeToString(it)
                    val encoded = URLEncoder.encode(json, StandardCharsets.UTF_8.toString())

                    navController.navigate(
                        "${Route.UpdateExpense}/${encoded}"
                    )
                }
            }
        }

    }
}
