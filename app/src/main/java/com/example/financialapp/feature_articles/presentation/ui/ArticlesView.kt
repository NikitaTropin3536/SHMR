package com.example.financialapp.feature_articles.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.financialapp.components.FinListItem
import com.example.financialapp.components.SpecialEditText
import com.example.financialapp.feature_articles.presentation.ArticlesEvent
import com.example.financialapp.feature_articles.presentation.ArticlesState

@Composable
fun ArticlesView(
    modifier: Modifier = Modifier,
    state: ArticlesState,
    onEvent: (ArticlesEvent) -> Unit,
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(
                state = scrollState,
            )
    ) {

        SpecialEditText(
            label = "Найти статью",
            previousData = "",
            onTextChanged = {
                /* TODO */
            }
        ) {

            onEvent(
                ArticlesEvent.OnSearchValueChanged(
                    searchValue = it
                )
            )

        }

        HorizontalDivider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceDim,
        )

        state.articles
            .filter { it.name.contains(state.searchValue) }
            .forEach {

                FinListItem(
                    emoji = it.emoji,
                    title = it.name,
                    height = 70.dp,
                    isClickable = false,
                    onClick = { }
                )

            }

    }
}