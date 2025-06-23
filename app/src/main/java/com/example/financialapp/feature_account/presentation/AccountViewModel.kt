package com.example.financialapp.feature_account.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financialapp.core.network.ErrorHandler
import com.example.financialapp.core.network.FinResult
import com.example.financialapp.feature_account.domain.usecase.GetAccountInfoUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AccountViewModel(
    private val billUseCase: GetAccountInfoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AccountState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    private val _action = MutableSharedFlow<AccountAction>()
    val action = _action.asSharedFlow()

    fun onEvent(
        event: AccountEvent
    ) {
        when (event) {
            is AccountEvent.OnLoadAccount -> {
                loadData()
            }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    status = FinResult.Loading
                )
            }

            val result = billUseCase.invoke()

            result.onSuccess { res ->
                if (res.isNotEmpty()) {
                    _state.update {
                        it.copy(
                            accounts = res,
                            status = FinResult.Success
                        )
                    }
                } else {
                    _state.update {
                        it.copy(
                            status = FinResult.Error
                        )
                    }

                    _action.emit(AccountAction.ShowSnackBar("Не удалось найти аккаунт"))
                }
            }.onFailure { err ->
                _state.update {
                    it.copy(
                        status = FinResult.Error
                    )
                }

                _action.emit(AccountAction.ShowSnackBar(ErrorHandler().handleException(err)))
            }
        }
    }
}
