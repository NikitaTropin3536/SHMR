package com.example.financialapp.feature_articles.presentation

sealed class ArticleAction {
    data class ShowSnackBar(val message: String) : ArticleAction()
}