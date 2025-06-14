package com.example.financialapp.feature_expenses.presentation.ui

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.financialapp.R
import com.example.financialapp.components.BottomBar
import com.example.financialapp.components.TopBar
import com.example.financialapp.feature_expenses.presentation.ExpensesViewModel

@Composable
fun ExpensesScreen(
    viewModel: ExpensesViewModel = ExpensesViewModel(),
    navController: NavController
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController
            )
        },

        topBar = {

            TopBar(
                title = "Расходы сегодня",
                icon = {
                    IconButton(
                        onClick = { /* TODO */ }
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

        floatingActionButtonPosition = FabPosition.End
    ) { padding ->

        ExpensesView(
            modifier = Modifier.padding(padding),
            state = state
        )

    }
}