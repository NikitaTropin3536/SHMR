package com.example.transations.di

import jakarta.inject.Scope

/**
 * Scope траназакций
 * указывает на жизненный цикл компонента
 */


@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class TransactionScope