package com.example.bill.presentation.current.ui

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.common.R
import com.example.bill.presentation.current.viewmodel.BillAction
import com.example.bill.presentation.current.viewmodel.BillEvent
import com.example.bill.presentation.current.viewmodel.BillViewModel
import com.example.common.navigation.Route
import com.example.common.ui.item.FinErrorMessage
import com.example.common.ui.item.FinLoadingBar
import com.example.common.ui.item.FinSnackBar
import com.example.common.ui.nav.FinBottomBar
import com.example.common.ui.nav.FinTopBar
import com.example.core.network.FinResult
import kotlinx.coroutines.flow.collectLatest

@Composable
fun BillScreen (
    navController: NavController,
    viewModel: BillViewModel
) {

    var error: String? by remember {
        mutableStateOf(null)
    }

    val state by viewModel.state.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.onEvent(BillEvent.OnLoadBill)

        viewModel.action.collectLatest { action ->
            when (action) {
                is BillAction.ShowSnackBar -> {
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
                title = stringResource(R.string.my_bill),
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Route.EditBill)
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_edit),
                            contentDescription = "изменить",
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

        when (state.status) {
            FinResult.Error -> {
                FinErrorMessage(
                    modifier = Modifier
                        .padding(padding),
                    text = error,
                    onUpdate = {
                        viewModel.onEvent(BillEvent.OnLoadBill)
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
                BillView(
                    modifier = Modifier.padding(padding),
                    state = state
                )
            }
        }

    }
}