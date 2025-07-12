package com.example.transations.presentation.expenses.history.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.common.R
import com.example.common.ui.field.FinDatePicker
import com.example.common.ui.item.FinListItem
import com.example.core.converter.toEmoji
import com.example.core.converter.toFormat
import com.example.transations.domain.model.TransactionModel
import com.example.transations.presentation.expenses.history.viewmodel.HistoryExpensesEvent
import com.example.transations.presentation.expenses.history.viewmodel.HistoryExpensesState

@Composable
fun HistoryExpensesView (
    modifier: Modifier = Modifier,
    state: HistoryExpensesState,
    onEvent: (HistoryExpensesEvent) -> Unit,
    onItemClick: (TransactionModel) -> Unit,
) {

    val scrollState = rememberScrollState()

    Column (
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(
                state = scrollState,
            )
    ){

        FinDatePicker(
            title = "Начало",
            previousValue = state.startDate,
            backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow
        ) {
            onEvent(HistoryExpensesEvent.OnChangedStartDate(it))
        }

        FinDatePicker (
            title = "Конец",
            previousValue = state.endDate,
            backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow
        ) {
            onEvent(HistoryExpensesEvent.OnChangedEndDate(it))
        }

        if (state.accounts.isNotEmpty()) {
            FinListItem(
                title = "Сумма",
                description = null,
                backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
                trailingText = "${state.transactions.sumOf { it.amount.toDouble() }.toFormat()} ${state.accounts[0].currency.toEmoji()}",
                isClickable = false,
                isShowDivider = false
            )
        }

        state.transactions
            .sortedBy {
                it.createdAt
            }
            .forEach {
                FinListItem(
                    trailingIcon = R.drawable.ic_light_arrow,
                    emoji = it.categoryModel.emoji,
                    title = it.categoryModel.name,
                    description = it.comment,
                    trailingSubText = it.transactionDate.substring(0, endIndex = 16).replace("T", " "),
                    trailingText = "${it.amount} ${state.accounts[0].currency.toEmoji()}",
                    height = 70.dp
                ) {
                    onItemClick(it)
                }
        }
    }
}


