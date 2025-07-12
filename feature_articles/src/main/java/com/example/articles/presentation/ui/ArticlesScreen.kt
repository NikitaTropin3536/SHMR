package com.example.articles.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.articles.presentation.viewmodel.ArticleAction
import com.example.articles.presentation.viewmodel.ArticlesEvent
import com.example.articles.presentation.viewmodel.ArticlesViewModel
import com.example.common.ui.item.FinLoadingBar
import com.example.common.ui.item.FinSnackBar
import com.example.common.ui.nav.BottomBar
import com.example.common.ui.nav.TopBar
import com.example.core.network.FinResult
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ArticlesScreen (
    viewModel: ArticlesViewModel,
    navController: NavController
) {
    
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.onEvent(ArticlesEvent.OnLoadArticles)

        viewModel.action.collectLatest { event ->
            when (event) {
                is ArticleAction.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(event.message)
                }

                else -> {}
            }
        }
    }

    Scaffold (
        bottomBar = {
            BottomBar(
                navController = navController
            )
        },
        topBar = {
            TopBar(
                title = "Мои статьи",
                actions = { }
            )
        },
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.onSurface,
        snackbarHost = { FinSnackBar(snackBarHostState) }
    ) { padding ->

        if (state.status != FinResult.Success) {
            FinLoadingBar(
                modifier = Modifier
                    .padding(padding)
            )
        } else {
            ArticlesView(
                modifier = Modifier.padding(padding),
                state = state,
                onEvent = {
                    viewModel.onEvent(it)
                }
            )
        }
    }
}
