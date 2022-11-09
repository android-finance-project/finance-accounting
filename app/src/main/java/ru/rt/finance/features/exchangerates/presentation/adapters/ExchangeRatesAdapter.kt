package ru.rt.finance.features.exchangerates.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import java.math.BigDecimal
import ru.rt.finance.R
import ru.rt.finance.databinding.CurrencyItemBinding
import ru.rt.finance.features.exchangerates.presentation.currency.Currency

class ExchangeRatesAdapter(
    val currencies: MutableList<Currency>
) : RecyclerView.Adapter<ExchangeRatesAdapter.ViewHolder>() {

    class ViewHolder(
        val itemBinding: CurrencyItemBinding
    ) : RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            CurrencyItemBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.currency_item, parent, false)
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currency = currencies[position]
        with(holder.itemBinding) {
            growingImage.isVisible = false
            currencySymbolImage.setImageResource(currency.symbol)
            baseCurrencySymbolImage.setImageResource(R.drawable.ic_baseline_currency_ruble_24)
            currency.growingIcon?.let {
                growingImage.isVisible = true
                growingImage.setImageResource(it)
            }
            valueTextView.text = currency.value.toPlainString()
        }
    }

    override fun getItemCount(): Int = currencies.size

    fun updateData(newCurrencies: Map<Int, Currency>) {
        currencies.forEachIndexed { index, currency ->
            newCurrencies[currency.symbol]?.let {
                if (currency.value != it.value) {
                    currencies[index] = currency.copy(
                        value = it.value,
                        growingIcon = isGrowing(currency.value, it.value)
                    )
                    notifyItemChanged(index)
                }
            }
        }
    }

    @DrawableRes
    private fun isGrowing(oldValue: BigDecimal, newValue: BigDecimal) =
        if (oldValue < newValue) {
            R.drawable.ic_baseline_arrow_drop_up_24
        } else {
            R.drawable.ic_baseline_arrow_drop_down_24
        }
}