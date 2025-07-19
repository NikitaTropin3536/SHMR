package com.example.transations.presentation.detail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.common.R
import com.example.common.ui.btn.FinancilityButton
import com.example.common.ui.field.FinancilityDayPicker
import com.example.common.ui.field.FinancilityDropDown
import com.example.common.ui.field.FinancilityEditText
import com.example.common.ui.field.FinancilityNumTextField
import com.example.common.ui.field.FinancilityTimePicker
import com.example.common.ui.item.FinancilityListItem
import com.example.common.ui.item.FinancilitySyncMessage
import com.example.transations.presentation.detail.viewmodel.UpdateTransactionEvent
import com.example.transations.presentation.detail.viewmodel.UpdateTransactionState

@Composable
fun UpdateTransactionView (
    modifier: Modifier = Modifier,
    state: UpdateTransactionState,
    isIncome: Boolean,
    onEvent: (UpdateTransactionEvent) -> Unit
) {
    Column (
        modifier = modifier
            .fillMaxSize()
    ){

        state.lastSync?.let {
            FinancilitySyncMessage(it)
        }

        if (state.accounts.isNotEmpty()) {
            FinancilityListItem (
                title = "Счет",
                trailingText = state.accounts[0].name,
                trailingIcon = R.drawable.ic_light_arrow
            )
        }

        if (state.articles.isNotEmpty()) {
            FinancilityDropDown(
                title = "Статья",
                options = state.articles,
                previousData = state.article?.name ?: "",
                onOptionSelected = {
                    onEvent(UpdateTransactionEvent.OnChoseArticle(it))
                },
            )
        }

        FinancilityNumTextField (
            title = "Сумма",
            previousData = state.sum ?: "",
        ) {
            onEvent(UpdateTransactionEvent.OnUpdateSum(it))
        }

        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceDim,
        )

        FinancilityDayPicker (
            title = "Дата",
            previousValue = state.date
        ) {
            onEvent(UpdateTransactionEvent.OnUpdateDate(it))
        }

        FinancilityTimePicker(
            title = "Выбери время",
            previousValue = state.time
        ) {
            onEvent(UpdateTransactionEvent.OnUpdateTime(it))
        }

        FinancilityEditText(
            previousData = state.comment ?: "",
            label = "Комментарий",
            isShowLeadingIcon = false,
            isShowTrailingIcon = false,
            backgroundColor = MaterialTheme.colorScheme.onSurface
        ) {
            onEvent(UpdateTransactionEvent.OnUpdateComment(it))
        }

        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceDim,
        )

        FinancilityButton(
            text = "Удалить ${if (isIncome) "доход" else "расход"}",
            onClick = {
                onEvent(UpdateTransactionEvent.OnDelete)
            }
        )
    }
}
