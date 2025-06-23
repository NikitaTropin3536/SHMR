package com.example.financialapp.core.converter

fun Double.toFormat(): String {
    return "%.2f".format(this)
}