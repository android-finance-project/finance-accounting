package ru.rt.finance.features.exchangerates.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import java.lang.IllegalArgumentException
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode
import java.time.LocalDateTime
import ru.rt.finance.features.exchangerates.data.model.ExchangeRateDto
import ru.rt.finance.features.exchangerates.domain.repository.ExchangeRatesRepository

private const val GET_RATES_PATH = "/latest"

/**
 * Репозиторий для запроса курса валют
 */
class ExchangeRatesRepositoryImpl(private val client: HttpClient) : ExchangeRatesRepository {

    override suspend fun getRates(): ExchangeRateDto = client.get(GET_RATES_PATH).body()
}