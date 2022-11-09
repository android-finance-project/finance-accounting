package ru.rt.finance.features.exchangerates.presentation.ext

import androidx.annotation.DrawableRes
import java.math.BigDecimal
import java.math.RoundingMode
import ru.rt.finance.R
import ru.rt.finance.features.exchangerates.data.model.ExchangeRateDto
import ru.rt.finance.features.exchangerates.presentation.currency.Currency
import ru.rt.finance.features.exchangerates.presentation.currency.CurrencyDisplay
import ru.rt.finance.features.exchangerates.presentation.currency.CurrencyType

fun ExchangeRateDto.toDisplay() =
    CurrencyDisplay(
        lastUpdate = timestamp,
        currencies = rates
            .map {
                Currency(
                    value = BigDecimal.ONE.divide(it.value, 2, RoundingMode.HALF_UP),
                    symbol = CurrencyType.valueOf(it.key).symbol(),
                )
            }.reversed()
    )

@DrawableRes
fun CurrencyType.symbol() = when (this) {
    CurrencyType.RUB -> R.drawable.ic_baseline_currency_ruble_24
    CurrencyType.USD -> R.drawable.ic_baseline_currency_dollar_24
    CurrencyType.EUR -> R.drawable.ic_baseline_currency_euro_24
    CurrencyType.CNY -> R.drawable.ic_baseline_currency_yuan_24
}