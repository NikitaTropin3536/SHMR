package com.example.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.common.R
import com.example.common.ui.field.FinToggleListItem
import com.example.common.ui.item.FinListItem

@Composable
fun SettingsView (
    modifier: Modifier = Modifier
) {

    val options = listOf("Основной цвет", "Звуки", "Хаптики", "Код пароль", "Синхронизация", "Язык", "О программе")

    Column (
        modifier = modifier
            .fillMaxSize()
    ){
        FinToggleListItem(
            title = "Светлая темная авто",
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