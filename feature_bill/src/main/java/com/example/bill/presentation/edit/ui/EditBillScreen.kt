package com.example.bill.presentation.edit.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.bill.presentation.edit.viewmodel.EditBillAction
import com.example.bill.presentation.edit.viewmodel.EditBillEvent
import com.example.bill.presentation.edit.viewmodel.EditBillViewModel
import com.example.common.navigation.Route
import com.example.common.ui.item.FinErrorMessage
import com.example.common.ui.item.FinLoadingBar
import com.example.common.ui.item.FinSnackBar
import com.example.common.ui.nav.FinBottomBar
import com.example.common.ui.nav.FinTopBar
import com.example.core.network.FinResult
import kotlinx.coroutines.flow.collectLatest

@Composable
fun EditBillScreen (
    navController: NavController,
    viewModel: EditBillViewModel
) {
    var error: String? by remember {
        mutableStateOf(null)
    }

    val state by viewModel.state.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.onEvent(EditBillEvent.OnLoadBill)

        viewModel.action.collectLatest { action ->
            when (action) {
                is EditBillAction.ShowSnackBar -> {
                    error = action.message

                    snackBarHostState.showSnackbar(action.message)
                }

                EditBillAction.OnOpenBill -> {
                    navController.navigate(Route.Bill)
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
                title = stringResource(com.example.bill.R.string.my_bill),
                actions = {
                    IconButton(
                        onClick = {
                            if (state.status == FinResult.Success) {
                                viewModel.onEvent(EditBillEvent.OnSaveBill)
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(com.example.common.R.drawable.ic_check),
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
                            painter = painterResource(com.example.common.R.drawable.ic_cross),
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
                        viewModel.onEvent(EditBillEvent.OnLoadBill)
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
                EditBillView(
                    modifier = Modifier.padding(padding),
                    state = state
                ) {
                    viewModel.onEvent(it)
                }
            }
        }
    }
}
