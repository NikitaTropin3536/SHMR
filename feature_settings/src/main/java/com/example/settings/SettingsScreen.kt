package com.example.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.common.ui.nav.FinancilityBottomBar
import com.example.common.ui.nav.FinancilityTopBar
import com.example.settings.SettingsView

@Composable
fun SettingsScreen (
    navController: NavController
) {
    Scaffold (
        bottomBar = {
            FinancilityBottomBar(
                navController = navController
            )
        },
        topBar = {
            FinancilityTopBar(
                title = "Настройки",
                actions = { }
            )
        },
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.onSurface,
    ) { padding ->

        SettingsView(
            modifier = Modifier.padding(padding)
        )

    }
}