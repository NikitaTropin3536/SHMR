package com.example.settings.presentation.viewmodel

import androidx.compose.ui.graphics.Color
import com.example.common.core.model.haptics.HapticEffect
import com.example.common.ui.theme.ThemeMode

sealed interface SettingsEvent {

    data object OnLoadData: SettingsEvent
    data class OnChooseSyncDuration(val duration: Float): SettingsEvent
    data class OnChooseLanguage(val lang: String): SettingsEvent
    data class OnUpdateTheme(val theme: ThemeMode): SettingsEvent
    data class OnSetColor(val color: Color): SettingsEvent
    data class OnEnterPin(val pin: String): SettingsEvent
    data class OnToggleHaptics(val enabled: Boolean): SettingsEvent
    data class OnSelectEffect(val effect: HapticEffect): SettingsEvent

}
