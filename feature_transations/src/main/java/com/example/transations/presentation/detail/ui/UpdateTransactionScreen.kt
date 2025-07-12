package com.example.transations.presentation.detail.ui

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
import com.example.common.R
import com.example.common.ui.item.FinLoadingBar
import com.example.common.ui.item.FinSnackBar
import com.example.common.ui.nav.BottomBar
import com.example.common.ui.nav.TopBar
import com.example.core.network.FinResult
import com.example.transations.domain.model.TransactionModel
import com.example.transations.presentation.detail.viewmodel.UpdateTransactionAction
import com.example.transations.presentation.detail.viewmodel.UpdateTransactionEvent
import com.example.transations.presentation.detail.viewmodel.UpdateTransactionViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun UpdateTransactionScreen (
    viewModel: UpdateTransactionViewModel,
    navController: NavController,
    transaction: TransactionModel,
    isIncome : Boolean,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.onEvent(
            UpdateTransactionEvent.OnLoadData(
                isIncome = isIncome,
                transaction = transaction
            )
        )

        viewModel.action.collectLatest { action ->
            when (action) {
                is UpdateTransactionAction.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(action.message)
                }

                UpdateTransactionAction.OnOpenScreen -> {
                    navController.popBackStack()
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
                title = "Детали ${if (isIncome) "дохода" else "расхода"}",
                actions = {
                    IconButton(
                        onClick = {
                            viewModel.onEvent(UpdateTransactionEvent.OnUpdate)
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
        snackbarHost = { FinSnackBar(snackBarHostState) }
    ) { padding ->

        if (state.status == FinResult.Loading) {
            FinLoadingBar(
                modifier = Modifier
                    .padding(padding)
            )
        } else {
            UpdateTransactionView (
                modifier = Modifier.padding(padding),
                state = state,
                isIncome = isIncome
            ) {
                viewModel.onEvent(it)
            }
        }

    }
}
