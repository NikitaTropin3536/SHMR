package com.example.transations.presentation.income.history.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.common.R
import com.example.common.core.model.transaction.TransactionModel
import com.example.common.ui.field.FinDatePicker
import com.example.common.ui.item.FinListItem
import com.example.common.ui.item.FinSyncMessage
import com.example.core.converter.toEmoji
import com.example.core.converter.toFormat
import com.example.storage.data.sync.AppSyncStorage
import com.example.transations.presentation.income.history.viewmodel.HistoryIncomeEvent
import com.example.transations.presentation.income.history.viewmodel.HistoryIncomeState

@Composable
fun HistoryIncomeView (
    modifier: Modifier = Modifier,
    state: HistoryIncomeState,
    onEvent: (HistoryIncomeEvent) -> Unit,
    onItemClick: (TransactionModel) -> Unit,
) {

    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val hapticSettings = AppSyncStorage(context).loadHaptics()

    Column (
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(
                state = scrollState,
            )
    ){

        state.lastSync?.let {
            FinSyncMessage(it)
        }

        FinDatePicker (
            title = "Начало",
            previousValue = state.startDate,
            backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow
        ) {
            onEvent(HistoryIncomeEvent.OnChangedStartDate(it))
        }

        FinDatePicker (
            title = "Конец",
            previousValue = state.endDate,
            backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow
        ) {
            onEvent(HistoryIncomeEvent.OnChangedEndDate(it))
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
                    height = 70.dp,
                    context = context,
                    hapticSettings = hapticSettings
                ) {
                    onItemClick(it)
                }
            }
    }
}
