package com.example.bill.presentation.current.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bill.domain.usecase.GetBillInfoUseCase
import com.example.bill.domain.usecase.GetTransactionsUseCase
import com.example.common.constants.Constants.BILL_SYNC
import com.example.common.constants.Constants.TRANSACTION_SYNC
import com.example.common.core.model.account.AccountBriefModel
import com.example.common.core.model.transaction.TransactionModel
import com.example.core.error.ErrorHandler
import com.example.core.error.OfflineDataException
import com.example.core.network.FinResult
import com.example.storage.data.sync.AppSyncStorage
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * VM для экрана счетов
 * */

class BillViewModel @Inject constructor(
    private val billInfoUseCase : GetBillInfoUseCase,
    private val appSyncStorage: AppSyncStorage,
    private val transactionUseCase: GetTransactionsUseCase,
): ViewModel() {

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

            val result = billInfoUseCase.invoke()

            result.onSuccess { res ->
                if (res.isNotEmpty()) {
                    _state.update {
                        it.copy(
                            accounts = res,
                            status = FinResult.Success
                        )
                    }

                    loadTransactions(res[0].id)

                } else {
                    _state.update {
                        it.copy(
                            status = FinResult.Error
                        )
                    }

                    _action.emit(BillAction.ShowSnackBar("Не удалось найти аккаунт"))
                }
            }.onFailure { err ->

                if (err is OfflineDataException) {
                    val accouns = err.data as List<AccountBriefModel>
                    _state.update {
                        it.copy(
                            status = FinResult.Success,
                            accounts = accouns,
                            lastSync = appSyncStorage.getSyncTime(
                                feature = BILL_SYNC,
                            )
                        )
                    }

                    loadTransactions(accouns[0].id)

                } else {
                    _state.update {
                        it.copy(status = FinResult.Error)
                    }

                    _action.emit(BillAction.ShowSnackBar(ErrorHandler().handleException(err)))
                }

            }
        }
    }

    private fun loadTransactions(
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
                startDate = LocalDate.now()
                    .withDayOfMonth(1)
                    .format(DateTimeFormatter.ISO_DATE),
                endDate = LocalDate.now()
                    .format(DateTimeFormatter.ISO_DATE)
            )

            result
                .onSuccess { res ->
                    _state.update {
                        it.copy(
                            status = FinResult.Success,
                            transactions = res
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

                        _action.emit(BillAction.ShowSnackBar(ErrorHandler().handleException(err)))
                    }
                }
        }
    }
}
