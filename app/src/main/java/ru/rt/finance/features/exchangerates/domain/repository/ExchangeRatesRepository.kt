package ru.rt.finance.features.exchangerates.domain.repository

import ru.rt.finance.features.exchangerates.data.model.ExchangeRateDto

/**
 * Репозиторий для запроса курса валют
 */
interface ExchangeRatesRepository {
    /**
     * Запрашивает валюты по их имени
     */
    suspend fun getRates(): ExchangeRateDto
}