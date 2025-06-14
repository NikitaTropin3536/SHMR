package com.example.financialapp.feature_income.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.financialapp.R
import com.example.financialapp.components.FinListItem
import com.example.financialapp.feature_income.presentation.IncomeState

@Composable
fun IncomeView(
    modifier: Modifier = Modifier,
    state: IncomeState
) {

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        FinListItem(
            title = "Всего",
            description = null,
            backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
            trailingText = "600 000 ₽",
            isClickable = false,
        ) {
            /* TODO */
        }

        state.transactions.forEach {

            FinListItem(
                trailingIcon = R.drawable.ic_light_arrow,
                emoji = null,
                title = it.categoryModel.name,
                description = null,
                trailingText = it.amount,
                height = 72.dp
            ) {
                /* TODO */
            }

        }

    }
}