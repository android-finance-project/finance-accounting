package ru.rt.finance.features.exchangerates.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
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
        holder.itemBinding.valueTextView.text = currencies[position].value.toPlainString()
    }

    override fun getItemCount(): Int = currencies.size
}