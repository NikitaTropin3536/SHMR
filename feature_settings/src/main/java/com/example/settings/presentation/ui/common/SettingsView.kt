package com.example.settings.presentation.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.common.navigation.Route
import com.example.common.ui.field.FinToggleListItem
import com.example.common.ui.item.FinListItem
import com.example.common.R

import com.example.settings.presentation.viewmodel.SettingsEvent
import com.example.settings.presentation.viewmodel.SettingsEvent.OnUpdateTheme
import com.example.settings.presentation.viewmodel.SettingsState
import com.example.storage.data.sync.AppSyncStorage

@Composable
fun SettingsView (
    modifier: Modifier = Modifier,
    onNavigate: (Route) -> Unit,
    state: SettingsState,
    onEvent: (SettingsEvent) -> Unit
) {

    val context = LocalContext.current
    val hapticSettings = AppSyncStorage(context).loadHaptics()

    val options = listOf(
        stringResource(com.example.settings.R.string.main_color),
        stringResource(com.example.settings.R.string.sound),

        stringResource(com.example.settings.R.string.haptics),
        stringResource(com.example.settings.R.string.code),
        stringResource(com.example.settings.R.string.sync),
        stringResource(com.example.settings.R.string.lang),

        stringResource(com.example.settings.R.string.about)
    )

    val routes = listOf(
        Route.ColorSettings,

        Route.AllSettings,

        Route.HapticsSettings,
        Route.CodeSettings,
        Route.SyncSettings,
        Route.LanguageSettings,

        Route.VersionSettings,
    )

    Column (
        modifier = modifier
            .fillMaxSize()
    ){

        FinToggleListItem(
            title = stringResource(com.example.settings.R.string.theme_color),
            current = state.theme
        ) {
            onEvent(OnUpdateTheme(it))
        }

        options.forEachIndexed { num, option ->
            FinListItem(
                trailingIcon = R.drawable.ic_dark_arrow,
                title = option,
                height = 56.dp,
                context = context,
                hapticSettings = hapticSettings
            ) {
                onNavigate(routes[num])
            }
        }
    }

}
