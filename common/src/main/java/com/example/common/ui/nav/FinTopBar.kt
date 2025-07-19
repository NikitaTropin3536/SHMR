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
fun FinancilityTopBar (
    title: String,
    containerColor: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.primary,
    navIcon: (@Composable () -> Unit)? = null,
    actions: (@Composable () -> Unit)? = null,
) {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(containerColor)
//            .systemBarsPadding()
//            .padding(start = 8.dp, end = 8.dp, top = 4.dp)
//    ) {
//        // Title по центру
//        Text(
//            text = title,
//            fontWeight = FontWeight.W400,
//            fontSize = 22.sp,
//            textAlign = TextAlign.Center,
//            color = MaterialTheme.colorScheme.inverseOnSurface,
//            modifier = Modifier.align(Alignment.Center)
//        )
//
//        // Навигационная иконка слева
//        navIcon?.let {
//            Box(modifier = Modifier.align(Alignment.CenterStart)) {
//                it()
//            }
//        }
//
//        // Actions справа
//        actions?.let {
//            Box(modifier = Modifier.align(Alignment.CenterEnd)) {
//                it()
//            }
//        }
//    }

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
