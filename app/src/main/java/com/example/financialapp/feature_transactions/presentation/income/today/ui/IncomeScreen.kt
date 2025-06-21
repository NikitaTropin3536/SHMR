package com.example.financialapp.feature_transactions.presentation.income.today.ui

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
import com.example.financialapp.R
import com.example.financialapp.components.item.FinLoadingBar
import com.example.financialapp.components.item.FinSnackBar
import com.example.financialapp.components.nav.BottomBar
import com.example.financialapp.components.nav.TopBar
import com.example.financialapp.core.network.FinResult
import com.example.financialapp.feature_transactions.presentation.income.today.IncomeAction
import com.example.financialapp.feature_transactions.presentation.income.today.IncomeEvent
import com.example.financialapp.feature_transactions.presentation.income.today.IncomeViewModel
import com.example.financialapp.navigation.Route
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun IncomeScreen(
    navController: NavController,
    viewModel: IncomeViewModel = koinViewModel<IncomeViewModel>()
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
                title = "Доходы сегодня",
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Route.HistoryIncomes)
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
                onClick = { /* TODO */ },
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
            )
        }

    }
}
