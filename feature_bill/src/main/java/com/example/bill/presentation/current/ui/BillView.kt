package com.example.bill.presentation.current.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.bill.R
import com.example.bill.presentation.current.viewmodel.BillState
import com.example.common.core.model.graphics.BarChartItem
import com.example.common.core.model.transaction.TransactionModel
import com.example.common.ui.graphics.BarChart
import com.example.common.ui.item.FinListItem
import com.example.common.ui.item.FinSyncMessage
import com.example.core.converter.toEmoji

@Composable
fun BillView (
    modifier: Modifier = Modifier,
    state: BillState,
) {

    Column (
        modifier = modifier
            .fillMaxSize()
    ){

        state.lastSync?.let {
            FinSyncMessage(it)
        }

        state.accounts.forEach {
            FinListItem(
                emoji = "\uD83D\uDC7B",
                title = stringResource(R.string.bill),
                description = null,
                backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
                trailingText = it.name,
                backgroundEmojiColor = Color.White,
                isClickable = false,
            )

            FinListItem(
                emoji = "\uD83D\uDCB0",
                title = stringResource(R.string.balance),
                description = null,
                backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
                trailingText = "${it.balance} ${it.currency.toEmoji()}",
                isClickable = false,
                backgroundEmojiColor = Color.White
            )



            FinListItem(
                title = stringResource(R.string.currency),
                description = null,
                backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
                trailingText = it.currency.toEmoji(),
                isClickable = false,
                isShowDivider = false,
            )
        }

//        val chartData = listOf(
//            BarChartItem("01.02", -50f),
//            BarChartItem("07.02", 30f),
//        )

        if (state.transactions.isNotEmpty()) {
            BarChart(
                data = getData(state.transactions),
                modifier = Modifier
                    .padding(vertical = 24.dp),
                maxBarHeight = 300.dp
            )
        }
    }
}

@Composable
private fun getData(
    transactions: List<TransactionModel>
) : List<BarChartItem> {
    val groupedByDate = transactions.groupBy {
        it.transactionDate.substring(startIndex = 5, endIndex = 10)
    }

    return groupedByDate.map { (date, transactionsOnDate) ->
        val incomeSum = transactionsOnDate
            .filter { it.categoryModel.isIncome }
            .sumOf { it.amount.toDouble() }

        val expenseSum = transactionsOnDate
            .filter { !it.categoryModel.isIncome }
            .sumOf { it.amount.toDouble() }

        BarChartItem(
            dateLabel = date,
            value = (incomeSum - expenseSum).toFloat(),
            color = if ((incomeSum - expenseSum).toFloat() <= 0) {
                MaterialTheme.colorScheme.tertiaryContainer
            } else MaterialTheme.colorScheme.surfaceTint
        )
    }.sortedBy { it.dateLabel }
}
