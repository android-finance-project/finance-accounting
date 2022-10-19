package ru.rt.finance.features.exchangerates.presentation.currency

import java.math.BigDecimal
import java.time.LocalDateTime

data class Currency(
    val value: BigDecimal = BigDecimal.ZERO,
    val type: CurrencyType,
)

data class CurrencyDisplay(
    val lastUpdate: LocalDateTime,
    val currencies: List<Currency>,
)