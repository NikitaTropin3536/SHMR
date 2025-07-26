package com.example.feature_splash.presentation.viewmodel

import com.example.core.network.FinResult

data class SplashState (
    val pin: String = "",
    val isPin: Boolean = false,
    val status: FinResult = FinResult.Loading
)