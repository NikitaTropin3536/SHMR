package com.example.transations.presentation.create.ui

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
import com.example.common.ui.item.FinancilityErrorMessage
import com.example.common.ui.item.FinancilityLoadingBar
import com.example.common.ui.item.FinancilitySnackBar
import com.example.common.ui.nav.FinancilityBottomBar
import com.example.common.ui.nav.FinancilityTopBar
import com.example.core.network.FinancilityResult
import com.example.transations.presentation.create.viewmodel.CreateTransactionAction
import com.example.transations.presentation.create.viewmodel.CreateTransactionEvent
import com.example.transations.presentation.create.viewmodel.CreateTransactionViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CreateTransactionScreen (
    viewModel: CreateTransactionViewModel,
    navController: NavController,
    isIncome : Boolean,
) {
    var error: String? by remember {
        mutableStateOf(null)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.onEvent(
            CreateTransactionEvent.OnLoadData(
                isIncome = isIncome
            )
        )

        viewModel.action.collectLatest { action ->
            when (action) {
                is CreateTransactionAction.ShowSnackBar -> {
                    error = action.message

                    snackBarHostState.showSnackbar(action.message)
                }

                CreateTransactionAction.OnOpenScreen -> {
                    navController.popBackStack()
                }
            }
        }
    }

    Scaffold (
        bottomBar = {
            FinancilityBottomBar(
                navController = navController
            )
        },
        topBar = {
            FinancilityTopBar(
                title = "Мои ${if (isIncome) "доходы" else "расходы"}",
                actions = {
                    IconButton(
                        onClick = {
                            viewModel.onEvent(CreateTransactionEvent.OnSave)
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_check),
                            contentDescription = "Сохранить",
                            tint = MaterialTheme.colorScheme.surfaceContainer
                        )
                    }
                },
                navIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_cross),
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
        snackbarHost = { FinancilitySnackBar(snackBarHostState) }
    ) { padding ->

        when (state.status) {
            FinancilityResult.Error -> {
                FinancilityErrorMessage(
                    modifier = Modifier
                        .padding(padding),
                    text = error,
                    onUpdate = {
                        CreateTransactionEvent.OnLoadData(
                            isIncome = isIncome
                        )
                    }
                )
            }
            FinancilityResult.Loading -> {
                FinancilityLoadingBar(
                    modifier = Modifier
                        .padding(padding)
                )
            }
            FinancilityResult.Success -> {
                CreateTransactionView(
                    modifier = Modifier.padding(padding),
                    state = state,
                    isIncome = isIncome
                ) {
                    viewModel.onEvent(it)
                }
            }
        }

    }
}
