package com.example.financialapp.feature_expenses.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financialapp.feature_expenses.domain.usecase.GetExpensesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExpensesViewModel : ViewModel() {

    private val _state = MutableStateFlow(ExpensesState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    private val transactionUseCase = GetExpensesUseCase()

    init {

        viewModelScope.launch {
            val transactions = transactionUseCase.invoke()
            _state.update {
                it.copy(
                    transactions = transactions
                )
            }
        }

    }
}
