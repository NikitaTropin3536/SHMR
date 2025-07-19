package com.example.feature_splash.presentation.viewmodel

sealed class SplashEvent {
    data object OnLoadData : SplashEvent()
}
