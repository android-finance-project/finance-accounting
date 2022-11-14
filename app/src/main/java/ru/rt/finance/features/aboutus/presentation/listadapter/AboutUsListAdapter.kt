package ru.rt.finance.features.aboutus.presentation.listadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import ru.rt.finance.databinding.ListItemAboutUsBinding
import ru.rt.finance.features.aboutus.presentation.data.AboutUsModel

class AboutUsListAdapter(
) :
    ListAdapter<AboutUsModel, AboutUsListAdapter.ViewHolder>(AboutUsDiffCallback()) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position],)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemAboutUsBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = currentList.size

    inner class ViewHolder(private val binding: ListItemAboutUsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AboutUsModel,) {
            with(binding) {
                textHeader.text = item.textHeader.toString()
                textLine1.text = item.textLine1.toString()
                textLine2.text = item.textLine2.toString()
                textLine3.text = item.textLine3.toString()
                textFooter.text = item.textFooter.toString()
            }
        }
    }
}

class AboutUsDiffCallback : DiffUtil.ItemCallback<AboutUsModel>() {
    override fun areItemsTheSame(oldItem: AboutUsModel, newItem: AboutUsModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: AboutUsModel, newItem: AboutUsModel): Boolean {
        return oldItem.textFooter == newItem.textFooter
    }
}