package com.example.bill.di.modules

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.example.bill.presentation.current.viewmodel.BillViewModel
import com.example.bill.presentation.edit.viewmodel.EditBillViewModel
import com.example.core.di.utils.ViewModelKey

/**
 * Модуль VM счета
 * */

@Module
interface BillViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(BillViewModel::class)
    fun bindBillViewModel(viewModel: BillViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditBillViewModel::class)
    fun bindEditBillViewModel(viewModel: EditBillViewModel): ViewModel

}
