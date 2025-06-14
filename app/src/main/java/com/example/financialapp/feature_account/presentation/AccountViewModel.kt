package com.example.financialapp.feature_account.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financialapp.feature_account.domain.usecase.GetAccountInfoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AccountViewModel : ViewModel() {

    private val _state = MutableStateFlow(AccountState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    private val accountUseCase = GetAccountInfoUseCase()

    init {
        viewModelScope.launch {
            val account = accountUseCase.invoke()
            _state.update {
                it.copy(
                    account = account
                )
            }
        }
    }
}
