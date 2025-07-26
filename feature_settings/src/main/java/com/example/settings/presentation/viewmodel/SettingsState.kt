package com.example.settings.presentation.viewmodel

import androidx.compose.ui.graphics.Color
import com.example.common.core.model.versions.AppInfo
import com.example.common.core.model.haptics.HapticSettings
import com.example.common.ui.theme.ThemeMode
import com.example.core.network.FinResult

data class SettingsState (
    val syncDuration: Float = 0f,

    val language: String = "",

    val theme: ThemeMode = ThemeMode.SYSTEM,
    val color: Color = Color(0xFFFFC107),

    val appInfo: AppInfo = AppInfo("null", "null"),

    val firstEntry: String = "",
    val confirmEntry: String = "",
    val stage: Int = 0,

    val haptics: HapticSettings = HapticSettings(),

    val status: FinResult = FinResult.Loading
)
