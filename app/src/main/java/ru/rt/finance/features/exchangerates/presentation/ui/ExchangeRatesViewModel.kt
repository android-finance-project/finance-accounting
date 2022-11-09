package ru.rt.finance.features.exchangerates.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.rt.finance.R
import ru.rt.finance.features.exchangerates.domain.ext.State
import ru.rt.finance.features.exchangerates.domain.usecases.LoadExchangeRatesUseCase
import ru.rt.finance.features.exchangerates.presentation.currency.CurrencyDisplay
import ru.rt.finance.features.exchangerates.presentation.ext.toDisplay

/**
 * View Model для курса валют
 */
class ExchangeRatesViewModel(private val useCase: LoadExchangeRatesUseCase) : ViewModel() {

    private lateinit var _exchangeHandleJob: Job
    private val _content = MutableStateFlow<ExchangeRatesState>(ExchangeRatesState.Loading)
    val state: StateFlow<ExchangeRatesState> = _content

    fun startHandle() {
        _exchangeHandleJob = handleContent()
    }

    fun stopHandle() = _exchangeHandleJob.cancel()

    private fun handleContent() = viewModelScope.launch {
        handleLoadingState()
        while (true) {
            withContext(Dispatchers.IO) {
                when (val result = useCase.getRates()) {
                    is State.Success -> handleDataState(result.data.toDisplay())
                    is State.Error -> handleErrorState()
                }
            }
            delay(defaultRequestDelay)
        }
    }

    private suspend fun handleDataState(data: CurrencyDisplay) =
        withContext(Dispatchers.Main) { _content.value = ExchangeRatesState.Data(data) }

    private suspend fun handleLoadingState() =
        withContext(Dispatchers.Main) { _content.value = ExchangeRatesState.Loading }

    private suspend fun handleErrorState() =
        withContext(Dispatchers.Main) {
            _content.value = ExchangeRatesState.Error(R.string.defaultError)
        }

    companion object {
        private val defaultRequestDelay = TimeUnit.MINUTES.toMillis(1)
    }
}