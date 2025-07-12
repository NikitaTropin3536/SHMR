package com.example.transations.presentation.income.today.ui

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.common.R
import com.example.common.navigation.Route
import com.example.common.ui.item.FinLoadingBar
import com.example.common.ui.item.FinSnackBar
import com.example.common.ui.nav.BottomBar
import com.example.common.ui.nav.TopBar
import com.example.core.network.FinResult
import com.example.transations.presentation.income.today.viewmodel.IncomeAction
import com.example.transations.presentation.income.today.viewmodel.IncomeEvent
import com.example.transations.presentation.income.today.viewmodel.IncomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun IncomeScreen (
    navController: NavController,
    viewModel: IncomeViewModel
) {

    val state by viewModel.state.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.onEvent(IncomeEvent.OnLoadTodayIncomes)

        viewModel.action.collectLatest { event ->
            when (event) {
                is IncomeAction.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(event.message)
                }

                else -> {}
            }
        }
    }

    Scaffold (
        bottomBar = {
            BottomBar(
                navController = navController
            )
        },
        topBar = {
            TopBar(
                title = "Доходы сегодня",
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Route.HistoryIncome)
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
                    navController.navigate(Route.CreateIncome)
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

        if (state.status != FinResult.Success) {
            FinLoadingBar(
                modifier = Modifier
                    .padding(padding)
            )
        } else {
            IncomeView(
                modifier = Modifier
                    .padding(padding),
                state = state
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
