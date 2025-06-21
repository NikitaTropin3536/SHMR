package com.example.financialapp.feature_settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.financialapp.R
import com.example.financialapp.components.field.FinToggleListItem
import com.example.financialapp.components.item.FinListItem

@Composable
fun SettingsView(
    modifier: Modifier = Modifier
) {

    val options = listOf(
        "Основной цвет",
        "Звуки",
        "Хаптики",
        "Код пароль",
        "Синхронизация",
        "Язык",
        "О программе"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        FinToggleListItem(
            title = "Светлая / темная тема",
            isChecked = false,
            onClick = {
                /* TODO */
            }
        )

        options.forEach {
            FinListItem(
                trailingIcon = R.drawable.ic_dark_arrow,
                title = it,
                height = 56.dp
            ) {
                /* TODO */
            }
        }
    }
}