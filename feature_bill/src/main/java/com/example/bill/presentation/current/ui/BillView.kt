package com.example.bill.presentation.current.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.bill.presentation.current.viewmodel.BillEvent
import com.example.bill.presentation.current.viewmodel.BillState
import com.example.common.ui.item.FinListItem
import com.example.core.converter.toEmoji

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BillView (
    modifier: Modifier = Modifier,
    state: BillState,
    onEvent: (BillEvent) -> Unit
) {

    Column (
        modifier = modifier
            .fillMaxSize()
    ){
        state.accounts.forEach {
            FinListItem(
                emoji = "\uD83D\uDC7B",
                title = "Счет",
                description = null,
                backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
                trailingText = it.name,
                backgroundEmojiColor = Color.White,
                isClickable = false,
            )

            FinListItem(
                emoji = "\uD83D\uDCB0",
                title = "Баланс",
                description = null,
                backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
                trailingText = "${it.balance} ${it.currency.toEmoji()}",
                isClickable = false,
                backgroundEmojiColor = Color.White
            )

            FinListItem(
                title = "Валюта",
                description = null,
                backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
                trailingText = it.currency.toEmoji(),
                isClickable = false,
                isShowDivider = false,
            )
        }
    }
}
