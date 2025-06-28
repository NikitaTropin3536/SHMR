package com.example.financialapp.feature_bill.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financialapp.core.error.ErrorHandler
import com.example.financialapp.core.network.FinResult
import com.example.financialapp.feature_bill.domain.usecase.GetBillInfoUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * VM для экрана счетов
 * */

class BillViewModel(
    private val accountUseCase: GetBillInfoUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(BillState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        _state.value
    )

    private val _action = MutableSharedFlow<BillAction>()
    val action = _action.asSharedFlow()

    fun onEvent(
        event: BillEvent
    ) {
        when (event) {
            is BillEvent.OnLoadBill -> {
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

            val result = accountUseCase.invoke()

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

                    _action.emit(BillAction.ShowSnackBar("Не удалось найти аккаунт"))
                }
            }.onFailure { err ->
                _state.update {
                    it.copy(
                        status = FinResult.Error
                    )
                }

                _action.emit(BillAction.ShowSnackBar(ErrorHandler().handleException(err)))
            }
        }
    }
}
