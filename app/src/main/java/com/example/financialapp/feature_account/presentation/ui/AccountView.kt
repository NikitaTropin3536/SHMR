package com.example.financialapp.feature_account.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.financialapp.R
import com.example.financialapp.components.item.FinListItem
import com.example.financialapp.core.converter.toEmoji
import com.example.financialapp.feature_account.presentation.AccountState

@Composable
fun AccountView(
    modifier: Modifier = Modifier,
    state: AccountState
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        state.accounts.forEach {
            FinListItem(
                emoji = "\uD83D\uDCB0",
                title = "Баланс",
                description = null,
                backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
                trailingText = "${it.balance} ${it.currency.toEmoji()}",
                trailingIcon = R.drawable.ic_light_arrow,
                backgroundEmojiColor = Color.White
            ) {
                /* TODO */
            }

            FinListItem(
                title = "Валюта",
                description = null,
                backgroundColor = MaterialTheme
                    .colorScheme.surfaceContainerLow,
                trailingText = it.currency.toEmoji(),
                trailingIcon = R.drawable.ic_light_arrow,
                isShowDivider = false,
            ) {
                /* TODO */
            }
        }

    }
}