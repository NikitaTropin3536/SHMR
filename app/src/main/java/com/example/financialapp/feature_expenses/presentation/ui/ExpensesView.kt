package com.example.financialapp.feature_expenses.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.financialapp.R
import com.example.financialapp.components.FinListItem
import com.example.financialapp.feature_expenses.presentation.ExpensesState

@Composable
fun ExpensesView(
    modifier: Modifier = Modifier,
    state: ExpensesState,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(
                state = scrollState,
            )
    ) {

        FinListItem(
            title = "Всего",
            description = null,
            backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
            trailingText = "436 558 ₽",
            isClickable = false,
        ) {
            /* TODO */
        }

        state.transactions.forEach {

            FinListItem(
                trailingIcon = R.drawable.ic_light_arrow,
                emoji = it.categoryModel.emoji,
                title = it.categoryModel.name,
                description = it.comment,
                trailingText = it.amount,
                height = 69.dp
            ) {
                /* TODO */
            }

        }
    }
}
