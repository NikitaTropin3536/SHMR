package com.example.transations.presentation.expenses.history.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.constants.Constants.TRANSACTION_SYNC
import com.example.common.core.model.transaction.TransactionModel
import com.example.core.error.ErrorHandler
import com.example.core.error.OfflineDataException
import com.example.core.network.FinResult
import com.example.storage.data.sync.AppSyncStorage
import com.example.transations.domain.usecase.GetAccountsUseCase
import com.example.transations.domain.usecase.GetTransactionsUseCase
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * VM экрана истории расходов
 * */

class HistoryExpensesViewModel @Inject constructor(
    private val accountsUseCase : GetAccountsUseCase,
    private val transactionUseCase: GetTransactionsUseCase,
    private val appSyncStorage: AppSyncStorage
) : ViewModel() {

    private val _state = MutableStateFlow(HistoryExpensesState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        _state.value
    )

    private val _action = MutableSharedFlow<HistoryExpensesAction>()
    val action = _action.asSharedFlow()


    fun onEvent(
        event: HistoryExpensesEvent
    ) {
        when (event) {
            is HistoryExpensesEvent.OnLoadExpenses -> {
                loadData()
            }

            is HistoryExpensesEvent.OnChangedEndDate -> {
                _state.update {
                    it.copy(
                        endDate = event.end
                    )
                }
                loadExpenses(state.value.accounts[0].id)
            }

            is HistoryExpensesEvent.OnChangedStartDate -> {
                _state.update {
                    it.copy(
                        startDate = event.start
                    )
                }
                loadExpenses(state.value.accounts[0].id)
            }
        }
    }

    private fun loadData() {
        loadAccounts {
            loadExpenses(it)
        }
    }

    private fun loadExpenses(
        id : Int
    ) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    status = FinResult.Loading,
                )
            }

            val result = transactionUseCase.invoke(
                id = id,
                startDate = state.value.startDate,
                endDate = state.value.endDate
            )

            result
                .onSuccess { res ->
                    _state.update {
                        it.copy(
                            status = FinResult.Success,
                            transactions = res.filter {
                                !it.categoryModel.isIncome
                            }
                        )
                    }
                }
                .onFailure { err ->
                    if (err is OfflineDataException) {
                        val transactions = err.data as List<TransactionModel>

                        _state.update {
                            it.copy(
                                status = FinResult.Success,
                                transactions = transactions.filter {
                                    !it.categoryModel.isIncome
                                },
                                lastSync = appSyncStorage.getSyncTime(
                                    feature = TRANSACTION_SYNC,
                                )
                            )
                        }
                    } else {
                        _state.update {
                            it.copy(status = FinResult.Error)
                        }

                        _action.emit(
                            HistoryExpensesAction.ShowSnackBar(
                                ErrorHandler().handleException(
                                    err
                                )
                            )
                        )
                    }
                }
        }
    }

    private fun loadAccounts(
        onSuccess : (Int) -> Unit,
    ) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    status = FinResult.Loading,
                )
            }

            val result = accountsUseCase.invoke()

            result
                .onSuccess { res ->
                    if (res.isNotEmpty()) {
                        _state.update {
                            it.copy(
                                accounts = res
                            )
                        }
                        onSuccess.invoke(res[0].id)
                    } else {
                        _state.update {
                            it.copy(
                                status = FinResult.Error,
                            )
                        }

                        _action.emit(HistoryExpensesAction.ShowSnackBar("Не удалось найти аккаунт"))
                    }
                }
                .onFailure { err ->
                    _state.update {
                        it.copy(
                            status = FinResult.Error,
                        )
                    }

                    _action.emit(
                        HistoryExpensesAction.ShowSnackBar(
                            ErrorHandler().handleException(
                                err
                            )
                        )
                    )
                }
        }
    }

}
