package com.example.financialapp

import androidx.annotation.StringRes

data class BottomNavItem(
    @StringRes val title: Int,
    val icon: Int,
    val route: String,
)