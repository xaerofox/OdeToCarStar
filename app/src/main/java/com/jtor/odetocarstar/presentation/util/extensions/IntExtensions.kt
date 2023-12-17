package com.jtor.odetocarstar.presentation.util.extensions

import java.text.NumberFormat
import java.util.Locale

fun Int.toCurrency(): String {
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())

    return currencyFormat.format(this)
}