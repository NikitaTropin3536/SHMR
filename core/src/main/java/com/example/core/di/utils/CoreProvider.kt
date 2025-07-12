package com.example.core.di.utils

import com.example.core.di.CoreComponent

/**
 * Интерфейс для имплементации [CoreComponent]
 */

interface CoreProvider {
    val coreComponent: CoreComponent
}
