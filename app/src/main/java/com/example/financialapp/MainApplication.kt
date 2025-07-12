package com.example.financialapp

import android.app.Application
import com.example.core.di.CoreComponent
import com.example.core.di.DaggerCoreComponent
import com.example.core.di.utils.CoreProvider

/**
 * При запуске приложения запускает Dagger и инициазизирует [coreComponent]
 * [coreComponent] - нужен для инъекции VM фабрики
 */

class MainApplication : Application(), CoreProvider {

    override val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
    }
}