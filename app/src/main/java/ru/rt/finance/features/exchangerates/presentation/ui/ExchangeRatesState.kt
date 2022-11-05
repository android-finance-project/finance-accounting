package ru.rt.finance.features.exchangerates.presentation.ui

import ru.rt.finance.features.exchangerates.presentation.currency.CurrencyDisplay

sealed class ExchangeRatesState {
    object Loading : ExchangeRatesState()
    data class Data(val data: CurrencyDisplay) : ExchangeRatesState()
    data class Error(val message: Int) : ExchangeRatesState()
}