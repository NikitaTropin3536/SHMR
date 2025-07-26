package com.example.bill.presentation.edit.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bill.presentation.edit.viewmodel.EditBillEvent
import com.example.bill.presentation.edit.viewmodel.EditBillState
import com.example.bill.R
import com.example.common.core.model.account.CurrencyOption
import com.example.common.ui.field.FinEditText
import com.example.common.ui.field.FinNumTextField
import com.example.common.ui.item.FinListItem
import com.example.common.ui.item.FinSyncMessage
import com.example.common.ui.sheet.FinCurrencySheet

@Composable
fun EditBillView (
    modifier: Modifier = Modifier,
    state: EditBillState,
    onEvent: (EditBillEvent) -> Unit
) {
    var isSheetOpen by remember { mutableStateOf(false) }

    if (isSheetOpen) {
        FinCurrencySheet(
            currencies = listOf(
                CurrencyOption("RUB", "₽", "Российский рубль ₽"),
                CurrencyOption("USD", "$", "Американский доллар $"),
                CurrencyOption("EUR", "€", "Евро €")
            ),
            onCurrencyClicked = {
                onEvent(EditBillEvent.OnChoseCurrency(it))
            },
        ) {
            isSheetOpen = false
        }
    }

    Column (
        modifier = modifier
            .fillMaxSize()
    ) {

        state.lastSync?.let {
            FinSyncMessage(it)
        }

        state.accounts.forEach {
            FinEditText(
                previousData = state.enteredName,
                label = it.name,
                backgroundColor = White,
                isShowTrailingIcon = false,
                isShowLeadingIcon = true,
            ) {
                onEvent(EditBillEvent.OnEnteredBillName(it))
            }

            HorizontalDivider(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth(),
                color = MaterialTheme.colorScheme.surfaceDim,
            )

            FinNumTextField(
                title = stringResource(R.string.balance),
                previousData = state.enteredAmount,
                backgroundColor = White,
            ) {
                onEvent(EditBillEvent.OnEnteredAmount(it))
            }

            HorizontalDivider(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth(),
                color = MaterialTheme.colorScheme.surfaceDim,
            )

            FinListItem(
                title = stringResource(R.string.currency),
                description = null,
                backgroundColor = White,
                trailingText = state.chosenCurrency.symbol,
                trailingIcon = com.example.common.R.drawable.ic_light_arrow,

                isShowDivider = false,
            ) {
                isSheetOpen = true
            }

        }
    }
}
