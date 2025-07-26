package com.example.bill.presentation.edit.viewmodel

import com.example.common.core.model.account.CurrencyOption

/**
 * События экрана редактирования счетов
 * */

sealed class EditBillEvent {
    data object OnLoadBill : EditBillEvent()
    data class OnChoseCurrency(val currency: CurrencyOption) : EditBillEvent()
    data class OnEnteredBillName(val name: String) : EditBillEvent()
    data class OnEnteredAmount(val amount: String) : EditBillEvent()
    data object OnSaveBill : EditBillEvent()
}
