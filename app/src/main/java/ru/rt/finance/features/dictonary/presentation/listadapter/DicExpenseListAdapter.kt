package ru.rt.finance.features.dictonary.presentation.listadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import ru.rt.finance.databinding.ListItemDicexpenseBinding
import ru.rt.finance.features.dictonary.data.model.dictionary.DicExpenseEntity

class DicExpenseListAdapter(
    private val OnEditDicExpenseClick: (position: Int) -> Unit = {}
) :
    ListAdapter<DicExpenseEntity, DicExpenseListAdapter.ViewHolder>(DicExpenseDiffCallback()) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position], OnEditDicExpenseClick)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemDicexpenseBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = currentList.size

    inner class ViewHolder(private val binding: ListItemDicexpenseBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DicExpenseEntity, OnEditDicExpenseClick: (position: Int) -> Unit = {}) {
            with(binding) {
                root.setOnClickListener {
                    OnEditDicExpenseClick(position)
                }
                textName.text = item.nameDicExpenseEntity
                textStatus.text = item.idDicExpenseEntity.toString()
            }
        }
    }
}

class DicExpenseDiffCallback : DiffUtil.ItemCallback<DicExpenseEntity>() {
    override fun areItemsTheSame(oldItem: DicExpenseEntity, newItem: DicExpenseEntity): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DicExpenseEntity, newItem: DicExpenseEntity): Boolean {
        return oldItem.idDicExpenseEntity == newItem.idDicExpenseEntity
    }
}