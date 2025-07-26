package com.example.transations.presentation.expenses.today.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
import com.example.transations.presentation.expenses.today.ui.ExpensesView
import com.example.transations.presentation.expenses.today.viewmodel.ExpensesAction
import com.example.transations.presentation.expenses.today.viewmodel.ExpensesEvent
import com.example.transations.presentation.expenses.today.viewmodel.ExpensesViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun ExpensesScreen (
    viewModel: ExpensesViewModel /*= koinViewModel<ExpensesViewModel>()*/,
    navController: NavController
) {

    var error: String? by remember {
        mutableStateOf(null)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.onEvent(ExpensesEvent.OnLoadTodayExpenses)

        viewModel.action.collectLatest { action ->
            when (action) {
                is ExpensesAction.ShowSnackBar -> {
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
                title = stringResource(com.example.transations.R.string.todat_expenses),
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Route.HistoryExpense)
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_history),
                            contentDescription = "История",
                            tint = MaterialTheme.colorScheme.surfaceContainer
                        )
                    }
                }
            )
        },
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.onSurface,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Route.CreateExpense)
                },
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_plus),
                    contentDescription = "add button",
                    modifier = Modifier
                        .size(16.dp),
                    tint = Color.White
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        snackbarHost = { FinSnackBar(snackBarHostState) }
    ) { padding ->

        when (state.status) {
            FinResult.Error -> {
                FinErrorMessage(
                    modifier = Modifier
                        .padding(padding),
                    text = error,
                    onUpdate = {
                        viewModel.onEvent(ExpensesEvent.OnLoadTodayExpenses)
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
                ExpensesView(
                    modifier = Modifier
                        .padding(padding),
                    state = state
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
