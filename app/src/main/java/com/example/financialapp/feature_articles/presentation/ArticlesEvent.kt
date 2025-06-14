package com.example.financialapp.feature_articles.presentation

sealed interface ArticlesEvent {
    data class OnSearchValueChanged(val searchValue: String): ArticlesEvent
}
