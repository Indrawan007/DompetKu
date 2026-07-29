package com.dompetku.app.presentation.adapter

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dompetku.app.data.local.entity.CategorySummary
import com.dompetku.app.databinding.ItemCategorySummaryBinding
import com.dompetku.app.util.CurrencyFormatter

class CategorySummaryAdapter :
    ListAdapter<CategorySummary, CategorySummaryAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategorySummaryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemCategorySummaryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CategorySummary) {
            with(binding) {
                // Color dot
                try {
                    val color = Color.parseColor(item.categoryColor)
                    val dot = GradientDrawable()
                    dot.shape = GradientDrawable.OVAL
                    dot.setColor(color)
                    viewColor.background = dot
                } catch (e: Exception) { }

                tvCategoryName.text = item.categoryName
                tvCount.text = "${item.transactionCount}x"
                tvAmount.text = CurrencyFormatter.format(item.totalAmount)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CategorySummary>() {
        override fun areItemsTheSame(
            oldItem: CategorySummary, newItem: CategorySummary
        ) = oldItem.categoryName == newItem.categoryName
        override fun areContentsTheSame(
            oldItem: CategorySummary, newItem: CategorySummary
        ) = oldItem == newItem
    }
}
