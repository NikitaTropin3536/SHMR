package com.example.financialapp.feature_transactions.presentation.expenses.today.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.financialapp.R
import com.example.financialapp.components.item.FinListItem
import com.example.financialapp.core.converter.toEmoji
import com.example.financialapp.core.converter.toFormat
import com.example.financialapp.feature_transactions.presentation.expenses.today.ExpensesState

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

        if (state.accounts.isNotEmpty()) {
            FinListItem(
                title = "Всего",
                description = null,
                backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
                trailingText = "${
                    state.transactions.sumOf { it.amount.toDouble() }.toFormat()
                } ${state.accounts[0].currency.toEmoji()}",
                isClickable = false,
            )
        }

        state.transactions.forEach {
            FinListItem(
                trailingIcon = R.drawable.ic_light_arrow,
                emoji = it.categoryModel.emoji,
                title = it.categoryModel.name,
                description = it.comment,
                trailingText = "${it.amount} ${state.accounts[0].currency.toEmoji()}",
                height = 69.dp
            ) {
                /* TODO */
            }
        }
    }
}
