package ru.rt.finance.features.exchangerates.presentation

import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.rt.finance.MainFragment
import ru.rt.finance.features.exchangerates.presentation.ui.ExchangeRatesViewModel

val exchangePresentation = module {
    fragment { MainFragment() }
    viewModel { ExchangeRatesViewModel(get()) }
}