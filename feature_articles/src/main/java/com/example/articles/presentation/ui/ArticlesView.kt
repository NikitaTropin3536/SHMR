package com.example.articles.presentation.ui

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.articles.R
import com.example.common.ui.field.FinEditText
import com.example.articles.presentation.viewmodel.ArticlesEvent
import com.example.articles.presentation.viewmodel.ArticlesState
import com.example.common.ui.item.FinListItem
import com.example.common.ui.item.FinSyncMessage

@Composable
fun ArticlesView (
    modifier: Modifier = Modifier,
    state: ArticlesState,
    onEvent: (ArticlesEvent) -> Unit,
) {

    val scrollState = rememberScrollState()

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

        FinEditText(
            previousData = "",
            label = stringResource(R.string.search_articles),
            isShowLeadingIcon = false,
            onTrailingIconClick = {
                onEvent(
                    ArticlesEvent.OnSearchValueChanged(
                    searchValue = it
                ))
            },
        ) {
            /* TODO */
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