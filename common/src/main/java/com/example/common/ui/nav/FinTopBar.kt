package com.example.common.ui.nav

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinTopBar (
    title: String,
    containerColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.primary,
    navIcon: (@Composable () -> Unit)? = null,
    actions: (@Composable () -> Unit)? = null,
) {
    CenterAlignedTopAppBar(

        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        },

        navigationIcon = {
            navIcon?.let {
                Box {
                    it()
                }
            }
        },

        actions = {
            actions?.let {
                Box {
                    it()
                }
            }
        },

        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = containerColor,
            titleContentColor = MaterialTheme.colorScheme.inverseOnSurface
        )

    )

}
