package ru.rt.finance.features.exchangerates.data.model

import java.math.BigDecimal
import java.time.LocalDateTime
import kotlinx.serialization.Serializable
import ru.rt.finance.features.exchangerates.data.util.DoubleToBigDecimalSerializer
import ru.rt.finance.features.exchangerates.data.util.LongToLocalDateTimeSerializer

@Serializable
data class ExchangeRateDto(
    val success: Boolean = false,
    @Serializable(with = LongToLocalDateTimeSerializer::class)
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val base: String = "",
    val rates: ExchangeRateMap = emptyMap()
)

private typealias ExchangeRateMap =
        Map<String, @Serializable(with = DoubleToBigDecimalSerializer::class) BigDecimal>