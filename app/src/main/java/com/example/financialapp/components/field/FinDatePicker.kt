package com.example.financialapp.components.field

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.financialapp.components.item.FinListItem
import com.example.financialapp.core.converter.convertDateToMillis
import com.example.financialapp.core.converter.convertMillisToDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinDatePicker(
    title: String,
    previousValue: String,
    backgroundColor: Color = MaterialTheme
        .colorScheme.onSurface,
    onChangeDate: (String) -> Unit
) {

//    TODO ставить выбранную дату

    var showDialog by remember { mutableStateOf(false) }
    val selectedDateLabel = remember { mutableStateOf(previousValue) }
    val datePickerState = rememberDatePickerState(
        initialDisplayMode = DisplayMode.Picker,
        initialSelectedDateMillis = convertDateToMillis(previousValue)
    )

    val pickerColors = DatePickerDefaults.colors(
        selectedDayContainerColor = MaterialTheme
            .colorScheme.primary,
        selectedYearContainerColor = MaterialTheme
            .colorScheme.primary,
        todayDateBorderColor = MaterialTheme
            .colorScheme.primary,

        containerColor = MaterialTheme
            .colorScheme.surfaceContainerLow,

        todayContentColor = MaterialTheme
            .colorScheme.inverseSurface,
        dayContentColor = MaterialTheme
            .colorScheme.inverseSurface,
        selectedYearContentColor = MaterialTheme
            .colorScheme.inverseSurface,
        selectedDayContentColor = MaterialTheme
            .colorScheme.inverseSurface,
        yearContentColor = MaterialTheme
            .colorScheme.inverseSurface,
        weekdayContentColor = MaterialTheme
            .colorScheme.inverseSurface,
        titleContentColor = MaterialTheme
            .colorScheme.inverseSurface,
        headlineContentColor = MaterialTheme
            .colorScheme.inverseSurface,
        subheadContentColor = MaterialTheme
            .colorScheme.inverseSurface,
        dayInSelectionRangeContentColor = MaterialTheme
            .colorScheme.inverseSurface,
    )


    if (showDialog) {
        DatePickerDialog(
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                decorFitsSystemWindows = false
            ),
            shape = RoundedCornerShape(15.dp),
            tonalElevation = 0.dp,
            onDismissRequest = {
                showDialog = false
            },
            colors = pickerColors,
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog = false

                        onChangeDate(selectedDateLabel.value)
                    }
                ) {
                    Text("OK")

                    selectedDateLabel.value =
                        convertMillisToDate(
                            datePickerState.selectedDateMillis ?: selectedDateLabel.value.toLong()
                        )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
                colors = pickerColors,
                title = null,
                headline = null,
                showModeToggle = false
            )
        }
    }

    FinListItem(
        title = title,
        description = null,
        backgroundColor = backgroundColor,
        trailingText = selectedDateLabel.value,
        isClickable = true,
    ) {
        showDialog = true
    }
}
