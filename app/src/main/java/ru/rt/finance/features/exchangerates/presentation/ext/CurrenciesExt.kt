package ru.rt.finance.features.exchangerates.presentation.ext

import ru.rt.finance.features.exchangerates.data.model.ExchangeRateDto
import ru.rt.finance.features.exchangerates.presentation.currency.Currency
import ru.rt.finance.features.exchangerates.presentation.currency.CurrencyDisplay
import ru.rt.finance.features.exchangerates.presentation.currency.CurrencyType

fun ExchangeRateDto.toDisplay() =
    CurrencyDisplay(
        lastUpdate = timestamp,
        currencies = rates.map { Currency(value = it.value, type = CurrencyType.valueOf(it.key)) }
    )