package ru.rt.finance.features.exchangerates.domain.usecases

import ru.rt.finance.features.exchangerates.domain.repository.ExchangeRatesRepository
import ru.rt.finance.features.exchangerates.presentation.currency.CurrencyType

/**
 * Use Case для курса валют
 *
 * @property repository репозиторий курса валют
 */
class LoadExchangeRatesUseCase(private val repository: ExchangeRatesRepository) {

    suspend fun getRates() = safeCall {
        repository.getRates().let { dto ->
            dto.copy(rates = dto.rates.filterKeys { currencyTypes.contains(it) })
        }
    }

    companion object {
        private val currencyTypes = CurrencyType.values()
            .filterNot { it == CurrencyType.RUB }
            .map { it.name }
    }
}