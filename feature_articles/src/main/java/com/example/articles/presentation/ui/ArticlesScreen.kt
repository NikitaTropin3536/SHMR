package com.example.articles.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.articles.presentation.ui.ArticlesView
import com.example.articles.presentation.viewmodel.ArticleAction
import com.example.articles.presentation.viewmodel.ArticlesEvent
import com.example.articles.presentation.viewmodel.ArticlesViewModel
import com.example.common.ui.item.FinancilityErrorMessage
import com.example.common.ui.item.FinancilityLoadingBar
import com.example.common.ui.item.FinancilitySnackBar
import com.example.common.ui.nav.FinancilityBottomBar
import com.example.common.ui.nav.FinancilityTopBar
import com.example.core.network.FinancilityResult
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ArticlesScreen (
    viewModel: ArticlesViewModel,
    navController: NavController
) {

    var error: String? by remember {
        mutableStateOf(null)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.onEvent(ArticlesEvent.OnLoadArticles)

        viewModel.action.collectLatest { action ->
            when (action) {
                is ArticleAction.ShowSnackBar -> {
                    error = action.message
                    snackBarHostState.showSnackbar(action.message)
                }
            }
        }
    }

    Scaffold (
        bottomBar = {
            FinancilityBottomBar(
                navController = navController
            )
        },
        topBar = {
            FinancilityTopBar(
                title = "Мои статьи",
                actions = { }
            )
        },
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.onSurface,
        snackbarHost = { FinancilitySnackBar(snackBarHostState) }
    ) { padding ->

        when (state.status) {
            FinancilityResult.Error -> {
                FinancilityErrorMessage(
                    modifier = Modifier
                        .padding(padding),
                    text = error,
                    onUpdate = {
                        viewModel.onEvent(ArticlesEvent.OnLoadArticles)
                    }
                )
            }
            FinancilityResult.Loading -> {
                FinancilityLoadingBar(
                    modifier = Modifier
                        .padding(padding)
                )
            }
            FinancilityResult.Success -> {
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
}
