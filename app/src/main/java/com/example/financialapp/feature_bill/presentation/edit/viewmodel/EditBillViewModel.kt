package com.example.financialapp.feature_bill.presentation.edit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financialapp.core.converter.toCurrency
import com.example.financialapp.core.error.ErrorHandler
import com.example.financialapp.core.network.FinResult
import com.example.financialapp.feature_bill.data.model.UpdateAccountDto
import com.example.financialapp.feature_bill.domain.model.AccountBriefModel
import com.example.financialapp.feature_bill.domain.usecase.GetBillInfoUseCase
import com.example.financialapp.feature_bill.domain.usecase.UpdateBillUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * VM для редактирование счетов
 * */

class EditBillViewModel (
    private val billInfoUseCase : GetBillInfoUseCase,
    private val updateBillUseCase : UpdateBillUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(EditBillState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        _state.value
    )

    private val _action = MutableSharedFlow<EditBillAction>()
    val action = _action.asSharedFlow()

    fun onEvent(
        event: EditBillEvent
    ) {
        when (event) {
            is EditBillEvent.OnEnteredBillName -> {
                _state.update {
                    it.copy(
                        enteredName = event.name
                    )
                }
            }
            EditBillEvent.OnLoadBill -> {
                loadData()
            }
            EditBillEvent.OnSaveBill -> {
                updateBill(
                    accDto = UpdateAccountDto(
                        name = state.value.enteredName,
                        balance = state.value.enteredAmount,
                        currency = state.value.chosenCurrency.code,
                    )
                )
            }

            is EditBillEvent.OnChoseCurrency -> {
                _state.update {
                    it.copy(
                        chosenCurrency = event.currency
                    )
                }
            }

            is EditBillEvent.OnEnteredAmount -> {
                _state.update {
                    it.copy(
                        enteredAmount = event.amount
                    )
                }
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

            val result = billInfoUseCase.invoke()

            result.onSuccess { res ->
                if (res.isNotEmpty()) {
                    _state.update {
                        it.copy(
                            accounts = res,
                            status = FinResult.Success,
                            enteredName = res[0].name,
                            chosenCurrency = res[0].currency.toCurrency(),
                            enteredAmount = res[0].balance
                        )
                    }
                } else {
                    _state.update {
                        it.copy(
                            status = FinResult.Error
                        )
                    }

                    _action.emit(EditBillAction.ShowSnackBar("Не удалось найти аккаунт"))
                }
            }.onFailure { err ->
                _state.update {
                    it.copy(
                        status = FinResult.Error
                    )
                }

                _action.emit(EditBillAction.ShowSnackBar(ErrorHandler().handleException(err)))
            }
        }
    }

    private fun updateBill(
        accDto : UpdateAccountDto
    ) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    status = FinResult.Loading
                )
            }

            val result = updateBillUseCase.invoke(
                id = state.value.accounts[0].id,
                newBill = accDto
            )

            result.onSuccess { res ->
                _state.update {
                    it.copy(
                        accounts = listOf<AccountBriefModel>(res),
                        status = FinResult.Success
                    )
                }

                _action.emit(EditBillAction.ShowSnackBar("Счет успешно обновлен"))

            }.onFailure { err ->
                _state.update {
                    it.copy(
                        status = FinResult.Error
                    )
                }

                _action.emit(EditBillAction.ShowSnackBar(ErrorHandler().handleException(err)))
            }
        }
    }


}
