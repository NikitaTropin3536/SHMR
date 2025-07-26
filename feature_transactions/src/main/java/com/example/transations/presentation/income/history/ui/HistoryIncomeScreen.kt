package com.example.transations.presentation.income.history.ui

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
import com.example.transations.presentation.income.history.viewmodel.HistoryIncomeAction
import com.example.transations.presentation.income.history.viewmodel.HistoryIncomeEvent
import com.example.transations.presentation.income.history.viewmodel.HistoryIncomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun HistoryIncomeScreen(
    viewModel: HistoryIncomeViewModel,
    navController: NavController
) {

    var error: String? by remember {
        mutableStateOf(null)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.onEvent(HistoryIncomeEvent.OnLoadIncomes)

        viewModel.action.collectLatest { action ->
            when (action) {
                is HistoryIncomeAction.ShowSnackBar -> {
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
                title = "Моя история",
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Route.AnalysisIncome)
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
                        viewModel.onEvent(HistoryIncomeEvent.OnLoadIncomes)
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
                HistoryIncomeView(
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
                        "${Route.UpdateIncome}/${encoded}"
                    )
                }
            }
        }

    }
}
