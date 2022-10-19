package ru.rt.finance.features.exchangerates.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.rt.finance.R
import ru.rt.finance.features.exchangerates.domain.ext.State
import ru.rt.finance.features.exchangerates.domain.usecases.LoadExchangeRatesUseCase
import ru.rt.finance.features.exchangerates.presentation.currency.CurrencyDisplay
import ru.rt.finance.features.exchangerates.presentation.ext.toDisplay

/**
 * View Model для курса валют
 */
class ExchangeRatesViewModel(private val useCase: LoadExchangeRatesUseCase) : ViewModel() {

    private val _content = MutableLiveData<ExchangeRatesState>()
    val state: LiveData<ExchangeRatesState> = _content

    init {
        handleContent()
    }

    private fun handleContent() = viewModelScope.launch {
        handleLoadingState()
        withContext(Dispatchers.IO) {
            when (val result = useCase.getRates()) {
                is State.Success -> handleDataState(result.data.toDisplay())
                is State.Error -> handleErrorState()
            }
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
}