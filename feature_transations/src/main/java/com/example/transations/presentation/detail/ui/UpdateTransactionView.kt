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
import com.example.common.ui.btn.FinButton
import com.example.common.ui.field.FinDatePicker
import com.example.common.ui.field.FinDropDown
import com.example.common.ui.field.FinEditText
import com.example.common.ui.field.FinNumTextField
import com.example.common.ui.field.FinTimePicker
import com.example.common.ui.item.FinListItem
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
        if (state.accounts.isNotEmpty()) {
            FinListItem(
                title = "Счет",
                trailingText = state.accounts[0].name,
                trailingIcon = R.drawable.ic_light_arrow
            )
        }

        if (state.articles.isNotEmpty()) {
            FinDropDown(
                title = "Статья",
                options = state.articles,
                previousData = state.article?.name ?: "",
                onOptionSelected = {
                    onEvent(UpdateTransactionEvent.OnChoseArticle(it))
                },
            )
        }

        FinNumTextField(
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

        FinDatePicker(
            title = "Дата",
            previousValue = state.date
        ) {
            onEvent(UpdateTransactionEvent.OnUpdateDate(it))
        }

        FinTimePicker(
            title = "Выбери время",
            previousValue = state.time
        ) {
            onEvent(UpdateTransactionEvent.OnUpdateTime(it))
        }

        FinEditText(
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

        FinButton(
            text = "Удалить ${if (isIncome) "доход" else "расход"}",
            onClick = {
                onEvent(UpdateTransactionEvent.OnDelete)
            }
        )
    }
}
