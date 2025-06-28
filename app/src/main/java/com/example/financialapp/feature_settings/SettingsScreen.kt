package com.example.financialapp.feature_settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.financialapp.components.nav.BottomBar
import com.example.financialapp.components.nav.TopBar

@Composable
fun SettingsScreen(
    navController: NavController
) {
    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController
            )
        },

        topBar = {
            TopBar(
                title = "Настройки",
                actions = { }
            )
        },

        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme
            .colorScheme.onSurface,
    ) { padding ->

        SettingsView(
            modifier = Modifier.padding(padding)
        )

    }
}