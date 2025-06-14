package com.example.financialapp.feature_articles.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.financialapp.components.BottomBar
import com.example.financialapp.components.TopBar
import com.example.financialapp.feature_articles.presentation.ArticlesViewModel

@Composable
fun ArticlesScreen(
    viewModel: ArticlesViewModel = ArticlesViewModel(),
    navController: NavController
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(

        bottomBar = {
            BottomBar(
                navController = navController
            )
        },

        topBar = {
            TopBar(
                title = "Мои статьи",
                icon = { }
            )
        },

        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.onSurface,
    ) { padding ->

        ArticlesView(
            modifier = Modifier.padding(padding),
            state = state,
            onEvent = {
                viewModel.onEvent(it)
            }
        )

    }
}
