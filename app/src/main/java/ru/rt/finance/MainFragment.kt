package ru.rt.finance

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import java.time.format.DateTimeFormatter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.rt.finance.databinding.FragmentMainBinding
import ru.rt.finance.features.exchangerates.presentation.adapters.ExchangeRatesAdapter
import ru.rt.finance.features.exchangerates.presentation.ui.ExchangeRatesState
import ru.rt.finance.features.exchangerates.presentation.ui.ExchangeRatesViewModel

class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    /* Exchange Rates */
    private lateinit var adapter: ExchangeRatesAdapter
    private val exchangeRatesViewModel by viewModel<ExchangeRatesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = ExchangeRatesAdapter(mutableListOf())
        binding.currencies.adapter = adapter

        viewLifecycleOwner.lifecycleScope
            .launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    exchangeRatesViewModel.startHandle()
                    exchangeRatesViewModel.state.collect(::handleState)
                }
            }
            .invokeOnCompletion { exchangeRatesViewModel.stopHandle() }
    }

    private fun handleState(state: ExchangeRatesState) {
        refreshState(state)
        @Suppress("NON_EXHAUSTIVE_WHEN_STATEMENT")
        when (state) {
            is ExchangeRatesState.Data -> state.handle()
            is ExchangeRatesState.Error -> state.handle()
        }
    }

    private fun refreshState(state: ExchangeRatesState) =
        with(binding) {
            exchangesProgressBar.isVisible = state is ExchangeRatesState.Loading
            currencies.isVisible = state is ExchangeRatesState.Data
            lastUpdateLayout.isVisible = state is ExchangeRatesState.Data
        }

    private fun ExchangeRatesState.Data.handle() {
        binding.lastUpdateText.text = data.lastUpdate.format(dateFormat)
        if (adapter.currencies.isEmpty()) {
            adapter.currencies.addAll(data.currencies)
        } else {
            adapter.updateData(data.currencies.associateBy { it.symbol })
        }
    }

    private fun ExchangeRatesState.Error.handle() =
        context?.let { Log.d("TAG", it.getString(message)) }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        private val dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
    }
}