package ru.rt.finance.features.exchangerates.presentation.currency

import androidx.annotation.DrawableRes
import java.math.BigDecimal
import java.time.LocalDateTime

data class Currency(
    val value: BigDecimal = BigDecimal.ZERO,
    @DrawableRes val symbol: Int,
    @DrawableRes val growingIcon: Int? = null,
)

data class CurrencyDisplay(
    val lastUpdate: LocalDateTime,
    val currencies: List<Currency>,
)