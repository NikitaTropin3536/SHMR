package com.example.financialapp.feature_transactions.presentation.expenses.create.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.financialapp.R
import com.example.financialapp.components.btn.FinButton
import com.example.financialapp.components.field.FinDatePicker
import com.example.financialapp.components.field.FinDropDown
import com.example.financialapp.components.field.FinEditText
import com.example.financialapp.components.field.FinNumTextField
import com.example.financialapp.components.field.FinTimePicker
import com.example.financialapp.components.item.FinListItem
import com.example.financialapp.feature_transactions.presentation.expenses.create.viewmodel.CreateExpensesEvent
import com.example.financialapp.feature_transactions.presentation.expenses.create.viewmodel.CreateExpensesState

@Composable
fun CreateExpensesView (
    modifier: Modifier = Modifier,
    state: CreateExpensesState,
    onEvent: (CreateExpensesEvent) -> Unit
) {
    Column (
        modifier = modifier
            .fillMaxSize()
    ){
        if (state.accounts.isNotEmpty()) {
            FinListItem (
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
                    onEvent(CreateExpensesEvent.OnChoseArticle(it))
                },
            )
        }

        FinNumTextField (
            title = "Сумма",
            previousData = state.sum ?: "",
        ) {
            onEvent(CreateExpensesEvent.OnEnterSum(it))
        }

        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceDim,
        )

        FinDatePicker (
            title = "Дата",
            previousValue = state.date
        ) {
            onEvent(CreateExpensesEvent.OnEnterDate(it))
        }

        FinTimePicker(
            title = "Выбери время",
            previousValue = state.time
        ) {
            onEvent(CreateExpensesEvent.OnEnterTime(it))
        }

        FinEditText(
            previousData = state.comment ?: "",
            label = stringResource(R.string.comm),
            isShowTrailingIcon = false,
            backgroundColor = MaterialTheme.colorScheme.onSurface
        ) {
            onEvent(CreateExpensesEvent.OnEnterComment(it))
        }

        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceDim,
        )

        FinButton(
            text = "Добавить расход",
            backgroundColor = MaterialTheme.colorScheme.primary,
            onClick = {
                onEvent(CreateExpensesEvent.OnSave)
            }
        )
    }
}