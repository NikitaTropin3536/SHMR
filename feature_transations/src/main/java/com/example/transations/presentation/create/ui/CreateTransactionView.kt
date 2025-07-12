package com.example.transations.presentation.create.ui

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
import com.example.transations.presentation.create.viewmodel.CreateTransactionEvent
import com.example.transations.presentation.create.viewmodel.CreateTransactionState

@Composable
fun CreateTransactionView (
    modifier: Modifier = Modifier,
    state: CreateTransactionState,
    isIncome: Boolean,
    onEvent: (CreateTransactionEvent) -> Unit
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
                    onEvent(CreateTransactionEvent.OnChoseArticle(it))
                },
            )
        }

        FinNumTextField(
            title = "Сумма",
            previousData = state.sum ?: "",
        ) {
            onEvent(CreateTransactionEvent.OnEnterSum(it))
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
            onEvent(CreateTransactionEvent.OnEnterDate(it))
        }

        FinTimePicker(
            title = "Выбери время",
            previousValue = state.time
        ) {
            onEvent(CreateTransactionEvent.OnEnterTime(it))
        }

        FinEditText(
            previousData = state.comment ?: "",
            label = "Комментарий",
            isShowLeadingIcon = false,
            isShowTrailingIcon = false,
            backgroundColor = MaterialTheme.colorScheme.onSurface
        ) {
            onEvent(CreateTransactionEvent.OnEnterComment(it))
        }

        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceDim,
        )

        FinButton(
            text = "Добавить ${if (isIncome) "доход" else "расход"}",
            backgroundColor = MaterialTheme.colorScheme.primary,
            onClick = {
                onEvent(CreateTransactionEvent.OnSave)
            }
        )
    }
}
