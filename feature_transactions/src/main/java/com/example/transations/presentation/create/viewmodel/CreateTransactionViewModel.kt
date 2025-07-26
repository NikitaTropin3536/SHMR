package com.example.transations.presentation.create.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.constants.Constants.TRANSACTION_SYNC
import com.example.common.core.model.category.CategoryModel
import com.example.core.error.ApiException
import com.example.core.error.ErrorHandler
import com.example.core.error.OfflineDataException
import com.example.core.network.FinResult
import com.example.storage.data.sync.AppSyncStorage
import com.example.transactions.data.dto.RequestTransactionDto
import com.example.transations.domain.usecase.GetAccountsUseCase
import com.example.transations.domain.usecase.GetArticlesUseCase
import com.example.transations.domain.usecase.PostTransactionUseCase
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * VM экрана добавления расходов
 * */

class CreateTransactionViewModel @Inject constructor (
    private val accountUseCase : GetAccountsUseCase,
    private val articlesUseCase : GetArticlesUseCase,
    private val createTransactionUseCase : PostTransactionUseCase,
    private val appSyncStorage: AppSyncStorage
) : ViewModel() {

    private val _state = MutableStateFlow(CreateTransactionState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        _state.value
    )

    private val _action = MutableSharedFlow<CreateTransactionAction>()
    val action = _action.asSharedFlow()

    fun onEvent(
        event: CreateTransactionEvent
    ) {
        when (event) {
            is CreateTransactionEvent.OnChoseArticle -> {
                _state.update {
                    it.copy(
                        article = event.article
                    )
                }
            }
            is CreateTransactionEvent.OnEnterComment -> {
                _state.update {
                    it.copy(
                        comment = event.comment
                    )
                }
            }
            is CreateTransactionEvent.OnEnterDate -> {
                _state.update {
                    it.copy(
                        date = event.date
                    )
                }
            }
            is CreateTransactionEvent.OnEnterSum -> {
                _state.update {
                    it.copy(
                        sum = event.sum
                    )
                }
            }
            is CreateTransactionEvent.OnEnterTime -> {
                _state.update {
                    it.copy(
                        time = event.time
                    )
                }
            }
            is CreateTransactionEvent.OnLoadData -> {
                loadAccount {
                    loadArticles(
                        isIncome = event.isIncome
                    )
                }
            }

            CreateTransactionEvent.OnSave -> {
                saveTransaction()
            }

        }
    }

    fun loadAccount(
        onSuccess: (Int) -> Unit
    ) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    status = FinResult.Loading
                )
            }

            val result = accountUseCase.invoke()

            result
                .onSuccess { res ->
                    _state.update {
                        it.copy(
                            accounts = res,
                            status = FinResult.Success
                        )
                    }

                    onSuccess(res[0].id)
                }
                .onFailure { err ->
                    _state.update {
                        it.copy(
                            status = FinResult.Error
                        )
                    }

                    _action.emit(CreateTransactionAction.ShowSnackBar(ErrorHandler().handleException(err)))
                }

        }
    }

    fun loadArticles(
        isIncome : Boolean
    ) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    status = FinResult.Loading
                )
            }

            val result = articlesUseCase.invoke()

            result
                .onSuccess { res ->
                    _state.update {
                        it.copy(
                            articles = res.filter { it.isIncome == isIncome },
                            status = FinResult.Success
                        )
                    }
                }
                .onFailure { err ->
                    if (err is OfflineDataException) {
                        val articles = err.data as List<CategoryModel>

                        _state.update {
                            it.copy(
                                status = FinResult.Success,
                                articles = articles.filter {
                                    it.isIncome == isIncome
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

                        _action.emit(CreateTransactionAction.ShowSnackBar(ErrorHandler().handleException(err)))
                    }
                }
        }
    }

    fun saveTransaction() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    status = FinResult.Loading
                )
            }

            try {
                val result = createTransactionUseCase.invoke(
                    RequestTransactionDto(
                        accountId = state.value.accounts[0].id,
                        categoryId = state.value.article?.id ?: throw ApiException("Заполните все поля"),
                        amount = state.value.sum ?: throw ApiException("Заполните все поля"),
                        transactionDate = "${state.value.date}T${state.value.time}:00.000Z",
                        comment = state.value.comment
                    )
                )

                result
                    .onSuccess { res ->
                        _state.update {
                            it.copy(
                                status = FinResult.Success
                            )
                        }

                        _action.emit(CreateTransactionAction.OnOpenScreen)
                    }
                    .onFailure { err ->
                        _state.update {
                            it.copy(
                                status = FinResult.Error
                            )
                        }

                        _action.emit(CreateTransactionAction.ShowSnackBar(ErrorHandler().handleException(err)))
                    }
            } catch (err : Exception) {
                // show business errors
                _state.update {
                    it.copy(
                        status = FinResult.Success
                    )
                }

                _action.emit(CreateTransactionAction.ShowSnackBar(ErrorHandler().handleException(err)))
            }
        }
    }
}
