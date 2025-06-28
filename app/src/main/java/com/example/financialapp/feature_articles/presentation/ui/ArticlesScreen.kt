package com.example.financialapp.feature_articles.presentation.ui

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
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.financialapp.R
import com.example.financialapp.components.item.FinLoadingBar
import com.example.financialapp.components.item.FinSnackBar
import com.example.financialapp.components.nav.BottomBar
import com.example.financialapp.components.nav.TopBar
import com.example.financialapp.core.network.FinResult
import com.example.financialapp.feature_articles.presentation.viewmodel.ArticleAction
import com.example.financialapp.feature_articles.presentation.viewmodel.ArticlesEvent
import com.example.financialapp.feature_articles.presentation.viewmodel.ArticlesViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun ArticlesScreen(
    viewModel: ArticlesViewModel = koinViewModel<ArticlesViewModel>(),
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
            }
        }
    }

    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController
            )
        },
        topBar = {
            TopBar(
                title = stringResource(R.string.my_articles),
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
